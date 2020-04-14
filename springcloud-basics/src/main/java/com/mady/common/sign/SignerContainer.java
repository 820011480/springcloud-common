//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mady.common.sign;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SignerContainer {

    private static Map<String, Signer> signerMap = new ConcurrentHashMap();

    public static Signer getSigner(String signerName) {
        return signerMap.get(signerName);
    }

    private SignerContainer() {}

    static {
        signerMap.put("RSA", SignerWithRsa.getInstance());
        signerMap.put("RSA2", SignerWithRsa2.getInstance());
    }
}
