package com.mady.common.utils;

import lombok.Data;



@Data
public class RSAKeyPair {

    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 公钥
     */
    private String publicKey;
}