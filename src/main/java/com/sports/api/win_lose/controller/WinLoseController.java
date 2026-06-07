package com.sports.api.win_lose.controller;

import com.sports.api.common.PageResult;
import com.sports.api.common.Result;
import com.sports.api.win_lose.service.WinLoseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/win-lose")
public class WinLoseController {
    private final WinLoseService winLoseService;
    public WinLoseController(WinLoseService winLoseService) { this.winLoseService = winLoseService; }

    @GetMapping("/list")
    public Result<PageResult<?>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer isTodayData) {
        var pageResult = winLoseService.getWinLoseList(page, pageSize, isTodayData);
        return Result.success(new PageResult<>(pageResult.getTotal(), pageResult.getCurrent(), pageResult.getSize(), pageResult.getRecords()));
    }
}
