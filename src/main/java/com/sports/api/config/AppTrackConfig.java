package com.sports.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 百度归因(AppTrack)配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "baidu.apptrack")
public class AppTrackConfig {

    /** 应用ID，固定值: 22870 */
    private String appId;

    /** 应用密钥，用于回调签名验证，仅服务端持有 */
    private String appSecret;
}
