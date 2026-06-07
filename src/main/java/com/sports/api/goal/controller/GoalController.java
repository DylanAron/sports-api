package com.sports.api.goal.controller;

import com.sports.api.common.PageResult;
import com.sports.api.common.Result;
import com.sports.api.goal.entity.Goal;
import com.sports.api.goal.service.GoalService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/goal")
public class GoalController {
    private final GoalService goalService;
    public GoalController(GoalService goalService) { this.goalService = goalService; }

    @GetMapping("/list")
    public Result<PageResult<?>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer isTodayData) {
        var pageResult = goalService.getGoalList(page, pageSize, isTodayData);
        return Result.success(new PageResult<>(pageResult.getTotal(), pageResult.getCurrent(), pageResult.getSize(), pageResult.getRecords()));
    }
}
