package com.sports.api.config;

import com.sports.api.common.BusinessException;
import com.sports.api.common.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public AuthInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new BusinessException(ResultCode.UNAUTHORIZED.getCode(), "未登录或token为空");
        }

        token = token.substring(7);
        if (!jwtUtil.validateToken(token)) {
            throw new BusinessException(ResultCode.UNAUTHORIZED.getCode(), "token已过期或无效");
        }

        Long userId = jwtUtil.getUserId(token);
        request.setAttribute("userId", userId);
        return true;
    }
}
