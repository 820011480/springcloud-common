package com.mady.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/17 11:03
 * @description 参数校验 [验签所使用的私秘钥需要存储到db或者保存起来]
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "param.check")
public class ParamCheckProperties {
    /**
     * 检查公共参数
     */
    private boolean commonParams;
    /**
     * 是否进行验签
     */
    private boolean checkSign;
    /**
     * 支持验签方式
     */
    private List<String> signType;
    /**
     * 支持字符集
     */
    private List<String> charsetType;
}
