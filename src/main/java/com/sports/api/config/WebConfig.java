package com.sports.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    public WebConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profile/**")
                .addResourceLocations("file:D:/soccer/uploadPath/");
        // 生产环境 Nginx 已配置 /profile/ 直接读磁盘，这行用于开发环境
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/user/login",
                        "/api/user/register",
                        "/api/content/**",
                        "/api/corner/**",
                        "/api/goal/**",
                        "/api/half-full/**",
                        "/api/score/**",
                        "/api/win-lose/**",
                        "/api/intelligence/**",
                        "/api/analysis/**",
                        "/api/attribution/callback",
                        "/api/app/config",
                        "/api/activation/**"
                );
    }
}
