package com.mady.common.sign;


import com.mady.common.utils.RsaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Map;

class SignerWithRsa2 extends AbstractSignerWithRsa {
    private static final Logger log = LoggerFactory.getLogger(SignerWithRsa2.class);
    public static final int ALGORITHM_KEY_LENGTH = 2048;
    private static SignerWithRsa2 instance = new SignerWithRsa2();

    public static SignerWithRsa2 getInstance() {
        return instance;
    }

    private SignerWithRsa2() {
        if (instance != null) {
            throw new RuntimeException("please do not new singleton instance.");
        }
    }

    public String doSign(Map<String, String> inputMap, String privateKeyB64) {
        return this.doSign(inputMap, privateKeyB64, "UTF-8");
    }

    public String doSign(Map<String, String> inputMap, String privateKeyB64, String charset) {
        String message = getStrToSign(inputMap);
        return this.doSign(message, privateKeyB64, charset);
    }

    public String doSign(String strToSign, String privateKeyB64) {
        return this.doSign(strToSign, privateKeyB64, "UTF-8");
    }

    public String doSign(String strToSign, String privateKeyB64, String charset) {
        try {
            byte[] message = strToSign.getBytes(charset);
            byte[] sign = RsaUtils.doSign(message, privateKeyB64, RsaUtils.RsaSignTypeEnum.SHA256_WITH_RSA);
            return Base64.getEncoder().encodeToString(sign);
        } catch (Exception var6) {
            throw new RuntimeException(var6);
        }
    }

    public Boolean doVerify(Map<String, String> inputMap, String sign, String pubkeyB64) {
        return this.doVerify(inputMap, sign, pubkeyB64, "UTF-8");
    }

    public Boolean doVerify(Map<String, String> inputMap, String sign, String pubkeyB64, String charset) {
        String strToSign = getStrToSign(inputMap);
        return this.doVerify(strToSign, sign, pubkeyB64, charset);
    }

    public Boolean doVerify(String strToSign, String sign, String pubkeyB64) {
        return this.doVerify(strToSign, sign, pubkeyB64, "UTF-8");
    }

    public Boolean doVerify(String strToSign, String sign, String pubkeyB64, String charset) {
        try {
            byte[] message = strToSign.getBytes(charset);
            byte[] signature = Base64.getDecoder().decode(sign);
            return RsaUtils.doVerify(message, signature, pubkeyB64, RsaUtils.RsaSignTypeEnum.SHA256_WITH_RSA);
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }
}
