package com.sports.api.attribution.controller;

import com.sports.api.attribution.service.AttributionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/attribution")
@RequiredArgsConstructor
public class AttributionController {

    private final AttributionService attributionService;

    /**
     * 百度归因回调接口
     * 接收百度 AppTrack 服务端发送的归因回调，记录激活/注册/付费等转化数据。
     *
     * 注意事项：
     * 1. 百度以 GET 方式请求（查询参数传递全部数据），部分场景可能使用 POST
     * 2. 返回格式必须为纯文本 "true"（非 JSON），否则百度会重试
     * 3. Content-Type 为 text/plain
     * 4. 本端点不要求用户认证，已在 WebConfig 中排除
     */
    @GetMapping(value = "/callback", produces = MediaType.TEXT_PLAIN_VALUE)
    public String callbackGet(HttpServletRequest request) {
        return processCallback(request);
    }

    /**
     * 部分百度归因使用 POST 方式回调，兼容处理
     */
    @PostMapping(value = "/callback", produces = MediaType.TEXT_PLAIN_VALUE)
    public String callbackPost(HttpServletRequest request) {
        return processCallback(request);
    }

    /**
     * 处理百度回调的通用逻辑
     */
    private String processCallback(HttpServletRequest request) {
        Map<String, String> params = extractParams(request);
        log.info("Received Baidu AppTrack callback, params keys: {}", params.keySet());

        boolean success = attributionService.handleCallback(params);
        return success ? "true" : "false";
    }

    /**
     * 提取请求中的所有参数（查询参数 + 表单参数）
     */
    private Map<String, String> extractParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            // 每个参数可能有多个值，取第一个
            params.put(name, request.getParameter(name));
        }
        return params;
    }
}
