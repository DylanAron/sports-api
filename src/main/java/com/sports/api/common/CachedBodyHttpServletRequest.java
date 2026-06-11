package com.sports.api.common;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.*;

/**
 * 缓存请求体的 HttpServletRequest 包装器
 * 支持重复读取 InputStream
 */
public class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {

    private final byte[] cachedBody;

    /** 从已知字符串创建（用于替换解密后的请求体，强制 Content-Type 为 JSON） */
    public CachedBodyHttpServletRequest(HttpServletRequest request, String body) {
        super(request);
        cachedBody = body.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }

    @Override
    public String getContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public ServletInputStream getInputStream() {
        return new CachedBodyServletInputStream(cachedBody);
    }

    @Override
    public BufferedReader getReader() {
        ByteArrayInputStream bais = new ByteArrayInputStream(cachedBody);
        return new BufferedReader(new InputStreamReader(bais));
    }

    /** 获取缓存的请求体字符串 */
    public String getCachedBodyAsString() {
        return new String(cachedBody, java.nio.charset.StandardCharsets.UTF_8);
    }

    private static class CachedBodyServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream bais;

        public CachedBodyServletInputStream(byte[] body) {
            this.bais = new ByteArrayInputStream(body);
        }

        @Override
        public int read() {
            return bais.read();
        }

        @Override
        public boolean isFinished() {
            return bais.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            // not needed
        }
    }
}
