package com.sports.api.corner.controller;

import com.sports.api.common.PageResult;
import com.sports.api.common.Result;
import com.sports.api.corner.dto.CornerVO;
import com.sports.api.corner.entity.Corner;
import com.sports.api.corner.service.CornerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/corner")
public class CornerController {

    private final CornerService cornerService;

    public CornerController(CornerService cornerService) {
        this.cornerService = cornerService;
    }

    @GetMapping("/list")
    public Result<PageResult<CornerVO>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer isTodayData) {
        var pageResult = cornerService.getCornerList(page, pageSize, isTodayData);
        List<CornerVO> list = pageResult.getRecords().stream()
                .map(CornerVO::from)
                .collect(Collectors.toList());
        return Result.success(new PageResult<>(pageResult.getTotal(), pageResult.getCurrent(), pageResult.getSize(), list));
    }

    @GetMapping("/detail/{id}")
    public Result<CornerVO> detail(@PathVariable Long id) {
        Corner corner = cornerService.getCornerDetail(id);
        return Result.success(CornerVO.from(corner));
    }
}
