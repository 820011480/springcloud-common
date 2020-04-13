package com.mady.common.sign;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.mady.common.sign.Signer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractSignerWithRsa implements Signer {
    private static final Logger log = LoggerFactory.getLogger(AbstractSignerWithRsa.class);

    AbstractSignerWithRsa() {
    }

    protected static String getStrToSign(Map<String, String> inputMap) {
        if (inputMap != null && !inputMap.isEmpty()) {
            inputMap.remove("signature");
            Object newMap;
            if (inputMap instanceof TreeMap) {
                newMap = inputMap;
            } else {
                newMap = new TreeMap(inputMap);
            }

            StringBuilder needSignStrBuilder = new StringBuilder();
            Iterator var3 = ((Map)newMap).entrySet().iterator();

            while(var3.hasNext()) {
                Entry<String, String> entry = (Entry)var3.next();
                needSignStrBuilder.append((String)entry.getKey()).append("=").append(entry.getValue() == null ? "" : (String)entry.getValue()).append("&");
            }

            return needSignStrBuilder.substring(0, needSignStrBuilder.length() - 1);
        } else {
            throw new RuntimeException("参数不能为空");
        }
    }
}
