//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mady.common.utils;

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
import java.util.Objects;
import javax.crypto.Cipher;

public final class RsaUtils {
    public static final String ALGORITHM_NAME = "RSA";
    public static final int MIN_KEY_LENGTH = 1024;

    public RsaUtils() {
    }

    public static byte[] doSign(byte[] message, String privateKeyB64, RsaUtils.RsaSignTypeEnum signType) throws Exception {
        PrivateKey privateKey = getPrivKeyFromPKCS8(privateKeyB64);
        Signature signature = Signature.getInstance(signType.getSignTypeString());
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    public static boolean doVerify(byte[] message, byte[] sign, String pubkeyB64, RsaUtils.RsaSignTypeEnum signType) throws Exception {
        PublicKey pubkey = getPubKeyFromX509(pubkeyB64);
        Signature signature = Signature.getInstance(signType.getSignTypeString());
        signature.initVerify(pubkey);
        signature.update(message);
        return signature.verify(sign);
    }

    public static byte[] encrypt(byte[] plainMessage, String pubkeyB64) throws Exception {
        PublicKey pubkey = getPubKeyFromX509(pubkeyB64);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, pubkey);
        return cipher.doFinal(plainMessage);
    }

    public static byte[] decrypt(byte[] encryptMessage, String privateKeyB64) throws Exception {
        PrivateKey privateKey = getPrivKeyFromPKCS8(privateKeyB64);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, privateKey);
        return cipher.doFinal(encryptMessage);
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

    public static RsaUtils.RSAKeyPair generateKey(int keyLength) throws Exception {
        if (keyLength >= 0 && keyLength % 1024 == 0) {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = new SecureRandom();
            keyPairGenerator.initialize(keyLength, secureRandom);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            RsaUtils.RSAKeyPair result = new RsaUtils.RSAKeyPair();
            result.setPrivateKey(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
            result.setPublicKey(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
            return result;
        } else {
            throw new IllegalArgumentException("rsa key length must be mod by 1024!");
        }
    }

    public static enum RsaSignTypeEnum {
        SHA1_WITH_RSA("SHA1WithRSA"),
        SHA256_WITH_RSA("SHA256withRSA");

        private String signTypeString;

        private RsaSignTypeEnum(String signTypeString) {
            this.signTypeString = signTypeString;
        }

        public String getSignTypeString() {
            return this.signTypeString;
        }
    }

    public static class RSAKeyPair {
        private String privateKey;
        private String publicKey;

        public RSAKeyPair() {
        }

        public String getPrivateKey() {
            return this.privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return this.publicKey;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o != null && this.getClass() == o.getClass()) {
                RsaUtils.RSAKeyPair that = (RsaUtils.RSAKeyPair)o;
                return Objects.equals(this.privateKey, that.privateKey) && Objects.equals(this.publicKey, that.publicKey);
            } else {
                return false;
            }
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.privateKey, this.publicKey});
        }
    }
}
