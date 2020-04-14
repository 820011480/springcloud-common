package com.mady.common.sign;

import lombok.Getter;

@Getter
public enum RsaSignTypeEnum {
    SHA1_WITH_RSA("SHA1WithRSA"),
    SHA256_WITH_RSA("SHA256withRSA");

    private String signTypeString;

    private RsaSignTypeEnum(String signTypeString) {
            this.signTypeString = signTypeString;
        }
}