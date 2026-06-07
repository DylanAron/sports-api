package com.sports.api.half_full.controller;

import com.sports.api.common.PageResult;
import com.sports.api.common.Result;
import com.sports.api.half_full.service.HalfFullService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/half-full")
public class HalfFullController {
    private final HalfFullService halfFullService;
    public HalfFullController(HalfFullService halfFullService) { this.halfFullService = halfFullService; }

    @GetMapping("/list")
    public Result<PageResult<?>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer isTodayData) {
        var pageResult = halfFullService.getHalfFullList(page, pageSize, isTodayData);
        return Result.success(new PageResult<>(pageResult.getTotal(), pageResult.getCurrent(), pageResult.getSize(), pageResult.getRecords()));
    }
}
