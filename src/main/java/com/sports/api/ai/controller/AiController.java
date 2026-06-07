package com.sports.api.ai.controller;

import com.sports.api.common.Result;
import com.sports.api.ai.service.AiService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/essay")
    public Result<Map<String, String>> getInspirationalEssay() {
        String essay = aiService.generateInspirationalEssay();
        return Result.success(Map.of("content", essay));
    }
}
