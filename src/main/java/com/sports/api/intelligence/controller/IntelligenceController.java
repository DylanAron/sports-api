package com.sports.api.intelligence.controller;

import com.sports.api.common.Result;
import com.sports.api.intelligence.entity.Intelligence;
import com.sports.api.intelligence.service.IntelligenceService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/intelligence")
public class IntelligenceController {
    private final IntelligenceService intelligenceService;
    public IntelligenceController(IntelligenceService intelligenceService) { this.intelligenceService = intelligenceService; }

    @GetMapping("/recent")
    public Result<List<Intelligence>> recent() {
        return Result.success(intelligenceService.getRecentWeek());
    }
}
