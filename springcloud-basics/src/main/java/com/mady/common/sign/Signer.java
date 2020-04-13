//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mady.common.sign;

import java.util.Map;

public interface Signer {
    String DEFAULT_ENCODING = "UTF-8";
    String STR_SIGNATURE = "signature";

    String doSign(Map<String, String> var1, String var2);

    String doSign(Map<String, String> var1, String var2, String var3);

    String doSign(String var1, String var2);

    String doSign(String var1, String var2, String var3);

    Boolean doVerify(Map<String, String> var1, String var2, String var3);

    Boolean doVerify(Map<String, String> var1, String var2, String var3, String var4);

    Boolean doVerify(String var1, String var2, String var3);

    Boolean doVerify(String var1, String var2, String var3, String var4);
}
