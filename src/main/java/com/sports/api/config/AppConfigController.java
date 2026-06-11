package com.sports.api.config;

import com.sports.api.common.Result;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用配置接口，客户端启动时获取配置（BD_APP_ID、AES密钥等）
 *
 * AES 密钥由配置文件的 sports.aes-key 注入（dev 写死，prod 从环境变量读取）。
 * 客户端写死相同密钥，不再依赖此接口获取密钥，此接口仅用于旧版本兼容。
 */
@RestController
@RequestMapping("/api/app")
@RequiredArgsConstructor
public class AppConfigController {

    private static final Logger log = LoggerFactory.getLogger(AppConfigController.class);

    private final AppTrackConfig appTrackConfig;

    @Value("${sports.aes-key}")
    private String aesKey;

    @GetMapping("/config")
    public Result<Map<String, Object>> getConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("bdAppId", appTrackConfig.getAppId());
        config.put("appSecret", appTrackConfig.getAppSecret());
        config.put("aesKey", aesKey);
        return Result.success(config);
    }
}
