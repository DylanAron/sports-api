package com.sports.api.activation.controller;

import com.sports.api.activation.dto.ActivationReportRequest;
import com.sports.api.activation.service.ActivationService;
import com.sports.api.common.Result;
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
    public Result<Void> report(@Valid @RequestBody ActivationReportRequest request) {
        activationService.report(request);
        return Result.success();
    }
}
