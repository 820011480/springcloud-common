package com.mady.common.utils;

import com.mady.common.sign.RsaSignTypeEnum;
import com.mady.common.sign.SignerWithRsa2;
import lombok.Data;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


/**
 * RSA加密工具类
 */

@Data
public final class RsaUtils {
    public static final String ALGORITHM_NAME = "RSA";
    public static final int MIN_KEY_LENGTH = 1024;

    /**
     * 生成密钥对
     * @param  keyLength
     * @return RSAKeyPair
     * @throws Exception
     */
    public static RSAKeyPair generateKey(int keyLength) throws Exception {
        if (keyLength >= 0 && keyLength % MIN_KEY_LENGTH == 0) {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = new SecureRandom();
            keyPairGenerator.initialize(keyLength, secureRandom);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            RSAKeyPair result = new RSAKeyPair();
            result.setPrivateKey(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
            result.setPublicKey(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
            return result;
        } else {
            throw new IllegalArgumentException("rsa key length must be mod by 1024!");
        }
    }

    public static byte[] doSign(byte[] message, String privateKeyB64, RsaSignTypeEnum signType) throws Exception {
        PrivateKey privateKey = getPrivKeyFromPKCS8(privateKeyB64);
        Signature signature = Signature.getInstance(signType.getSignTypeString());
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    public static boolean doVerify(byte[] message, byte[] sign, String pubkeyB64, RsaSignTypeEnum signType) throws Exception {
        PublicKey pubkey = getPubKeyFromX509(pubkeyB64);
        Signature signature = Signature.getInstance(signType.getSignTypeString());
        signature.initVerify(pubkey);
        signature.update(message);
        return signature.verify(sign);
    }

    public static byte[] encrypt(byte[] plainMessage, String pubkeyB64) throws Exception {
        PublicKey pubkey = getPubKeyFromX509(pubkeyB64);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubkey);
        return cipher.doFinal(plainMessage);
    }


    public static String encrypt(String plainMessage, String pubkeyB64) throws Exception {
        byte[] decode = plainMessage.getBytes("UTF-8");
        PublicKey pubKey = getPubKeyFromX509(pubkeyB64);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(decode));
    }


    public static byte[] decrypt(byte[] encryptMessage, String privateKeyB64) throws Exception {
        PrivateKey privateKey = getPrivKeyFromPKCS8(privateKeyB64);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptMessage);
    }


    public static String decrypt(String encryptMessage, String privateKeyB64) throws Exception {
        byte[] decode = Base64.getDecoder().decode(encryptMessage);
        PrivateKey privateKey = getPrivKeyFromPKCS8(privateKeyB64);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String (cipher.doFinal(decode), "UTF-8");
    }

    private static PrivateKey getPrivKeyFromPKCS8(String prikeyB64) throws Exception {
        byte[] keyByte = Base64.getDecoder().decode(prikeyB64);
        KeyFactory keyfactory = KeyFactory.getInstance("RSA");
        KeySpec keySpec = new PKCS8EncodedKeySpec(keyByte);
        return keyfactory.generatePrivate(keySpec);
    }

    private static PublicKey getPubKeyFromX509(String pubkeyB64) throws Exception {
        byte[] keyByte = Base64.getDecoder().decode(pubkeyB64);
        KeyFactory keyfactory = KeyFactory.getInstance("RSA");
        KeySpec keySpec = new X509EncodedKeySpec(keyByte);
        return keyfactory.generatePublic(keySpec);
    }


    /**
     * 测试方法
     * 私钥加密 公钥解密
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        RSAKeyPair rsaKeyPair = generateKey(2048);
        System.out.println("公钥:" + rsaKeyPair.getPublicKey());
        System.out.println("私钥:" + rsaKeyPair.getPrivateKey());
        //加签:
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("book", "book");
        hashMap.put("cat", "cat");
        hashMap.put("dog", "dog");
        String text = SignerWithRsa2.getInstance().getStrToSign(hashMap);
        System.out.println();
        byte[] encrypt = encrypt(text.getBytes("UTF-8"), rsaKeyPair.getPublicKey());
        String encodeToString = Base64.getEncoder().encodeToString(encrypt);
        System.out.println("加密后的字符串:" + encodeToString);
        byte[] decode = Base64.getDecoder().decode(encodeToString);
        byte[] decrypt = decrypt(decode, rsaKeyPair.getPrivateKey());
        System.out.println("解密后的字符串:" + new String(decrypt, "UTF-8"));
        System.out.println("==================================================");
        String encrypt1 = encrypt(text, rsaKeyPair.getPublicKey());
        System.out.println("加密后的字符串:" + encrypt1);
        System.out.println("解密后的字符串:" + decrypt(encrypt1, rsaKeyPair.getPrivateKey()));
        System.out.println("==================================================");
        byte[] bytes = doSign(text.getBytes("UTF-8"), rsaKeyPair.getPrivateKey(), RsaSignTypeEnum.SHA256_WITH_RSA);
        System.out.println( "加签：" + bytes);
        boolean doVerify = doVerify(text.getBytes("UTF-8"), bytes, rsaKeyPair.getPublicKey(), RsaSignTypeEnum.SHA256_WITH_RSA);
        System.out.println("验签:" + doVerify);
    }
}
