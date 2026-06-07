package com.sports.api.score.controller;

import com.sports.api.common.PageResult;
import com.sports.api.common.Result;
import com.sports.api.score.service.ScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/score")
public class ScoreController {
    private final ScoreService scoreService;
    public ScoreController(ScoreService scoreService) { this.scoreService = scoreService; }

    @GetMapping("/list")
    public Result<PageResult<?>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer isTodayData) {
        var pageResult = scoreService.getScoreList(page, pageSize, isTodayData);
        return Result.success(new PageResult<>(pageResult.getTotal(), pageResult.getCurrent(), pageResult.getSize(), pageResult.getRecords()));
    }
}
