package com.sports.api.banner.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sports.api.banner.dto.BannerVO;
import com.sports.api.banner.entity.Banner;
import com.sports.api.banner.mapper.BannerMapper;
import com.sports.api.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/content")
public class BannerController {

    private final BannerMapper bannerMapper;

    public BannerController(BannerMapper bannerMapper) {
        this.bannerMapper = bannerMapper;
    }

    @GetMapping("/banners")
    public Result<List<BannerVO>> banners() {
        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Banner::getStatus, 1);
        wrapper.orderByAsc(Banner::getSortOrder);
        List<Banner> list = bannerMapper.selectList(wrapper);
        List<BannerVO> voList = list.stream().map(BannerVO::from).collect(Collectors.toList());
        return Result.success(voList);
    }
}
