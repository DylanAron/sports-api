package com.sports.api.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 请求/响应加密过滤器
 * /api/v2/** 强制加解密，不再依赖 X-Encrypted 头。
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
public class EncryptionFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(EncryptionFilter.class);

    private final String aesKey;

    public EncryptionFilter(@Value("${sports.aes-key}") String aesKey) {
        this.aesKey = aesKey;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();

        // 配置接口和百度回调走明文
        if (path.contains("/api/app/config") || path.contains("/api/attribution/callback")) {
            chain.doFilter(request, response);
            return;
        }

        // 非 v2 路径走明文
        String apiVersion = (String) httpRequest.getAttribute("apiVersion");
        if (!"v2".equals(apiVersion)) {
            chain.doFilter(request, response);
            return;
        }

        // v2 加密通道：解密请求体
        String body = readBody(httpRequest.getInputStream());
        boolean bodyDecrypted = false;
        if (body != null && !body.isBlank()) {
            try {
                body = CryptoUtil.decrypt(body, aesKey);
                bodyDecrypted = true;
                request = new CachedBodyHttpServletRequest(httpRequest, body);
            } catch (Exception e) {
                log.warn("decrypt body failed for {}: {}", path, e.getMessage());
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpResponse.setContentType("application/json; charset=utf-8");
                httpResponse.getWriter().write("{\"code\":400,\"message\":\"decrypt failed\"}");
                return;
            }
        }

        // 包装响应
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);

        // 加密响应体
        byte[] responseBody = responseWrapper.getContentAsByteArray();
        if (responseBody.length > 0) {
            try {
                String plain = new String(responseBody, StandardCharsets.UTF_8);
                String encrypted = CryptoUtil.encrypt(plain, aesKey);
                responseWrapper.resetBuffer();
                responseWrapper.getOutputStream().write(encrypted.getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                log.warn("encrypt response failed for {}: {}", path, e.getMessage());
            }
        }
        responseWrapper.copyBodyToResponse();
    }

    private String readBody(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        while ((read = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        return baos.toString(StandardCharsets.UTF_8);
    }
}
