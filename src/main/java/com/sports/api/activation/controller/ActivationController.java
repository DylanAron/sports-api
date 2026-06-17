package com.sports.api.activation.controller;

import com.sports.api.activation.dto.ActivationReportRequest;
import com.sports.api.activation.service.ActivationService;
import com.sports.api.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activation")
public class ActivationController {

    private final ActivationService activationService;

    public ActivationController(ActivationService activationService) {
        this.activationService = activationService;
    }

    @PostMapping("/report")
    public Result<Void> report(@Valid @RequestBody ActivationReportRequest request,
                               HttpServletRequest httpRequest) {
        // 从请求中获取客户端真实 IP
        String ip = httpRequest.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank() || "unknown".equalsIgnoreCase(ip)) {
            ip = httpRequest.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isBlank() || "unknown".equalsIgnoreCase(ip)) {
            ip = httpRequest.getRemoteAddr();
        }
        // 取第一个 IP（X-Forwarded-For 可能含逗号分隔的多级代理）
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        request.setIp(ip);

        activationService.report(request);
        return Result.success();
    }
}
