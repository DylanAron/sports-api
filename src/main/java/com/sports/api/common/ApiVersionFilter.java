package com.sports.api.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * API 版本控制过滤器
 * 将 /api/v2/** 重写为 /api/**，并设置 apiVersion=v2 属性用于加密开关
 * 旧版本的 /api/** 保持不动
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ApiVersionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();

        if (path.startsWith("/api/v2/")) {
            // 重写路径：/api/v2/xxx -> /api/xxx
            String newPath = path.replaceFirst("/api/v2/", "/api/");
            httpRequest.setAttribute("apiVersion", "v2");
            httpRequest = new VersionedRequest(httpRequest, newPath);
        }

        chain.doFilter(httpRequest, response);
    }

    private static class VersionedRequest extends HttpServletRequestWrapper {
        private final String newPath;

        public VersionedRequest(HttpServletRequest request, String newPath) {
            super(request);
            this.newPath = newPath;
        }

        @Override
        public String getRequestURI() {
            return newPath;
        }

        @Override
        public StringBuffer getRequestURL() {
            StringBuffer url = ((HttpServletRequest) getRequest()).getRequestURL();
            String origPath = ((HttpServletRequest) getRequest()).getRequestURI();
            return new StringBuffer(url.toString().replace(origPath, newPath));
        }

        @Override
        public String getServletPath() {
            return newPath;
        }
    }
}
