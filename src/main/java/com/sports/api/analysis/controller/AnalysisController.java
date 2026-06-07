package com.sports.api.analysis.controller;

import com.sports.api.analysis.entity.Analysis;
import com.sports.api.analysis.service.AnalysisService;
import com.sports.api.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping("/recent")
    public Result<List<Analysis>> recent() {
        List<Analysis> list = analysisService.getRecentAnalysis();
        return Result.success(list);
    }
}
