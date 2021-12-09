package com.aisiono.store.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wuxiang
 * @date 2021/12/9 9:21 上午
 */
@Data
@Component
@ConfigurationProperties(prefix = "oos")
public class ConstantProperties {

    private  String endpoint;
    private  String keyId;
    private  String keySecret;
    private  String bucketName;
    private  String fileHost;

}
