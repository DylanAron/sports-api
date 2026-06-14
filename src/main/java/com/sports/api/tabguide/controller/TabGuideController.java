package com.sports.api.tabguide.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sports.api.common.Result;
import com.sports.api.tabguide.dto.TabGuideVO;
import com.sports.api.tabguide.entity.TabGuide;
import com.sports.api.tabguide.mapper.TabGuideMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/content")
public class TabGuideController {

    private final TabGuideMapper tabGuideMapper;

    public TabGuideController(TabGuideMapper tabGuideMapper) {
        this.tabGuideMapper = tabGuideMapper;
    }

    @GetMapping("/tab-guides")
    public Result<List<TabGuideVO>> tabGuides() {
        LambdaQueryWrapper<TabGuide> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TabGuide::getStatus, 1);
        List<TabGuide> list = tabGuideMapper.selectList(wrapper);
        List<TabGuideVO> voList = list.stream().map(TabGuideVO::from).collect(Collectors.toList());
        return Result.success(voList);
    }
}
