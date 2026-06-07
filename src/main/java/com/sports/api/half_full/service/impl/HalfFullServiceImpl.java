package com.sports.api.half_full.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sports.api.half_full.entity.HalfFull;
import com.sports.api.half_full.mapper.HalfFullMapper;
import com.sports.api.half_full.service.HalfFullService;
import org.springframework.stereotype.Service;

@Service
public class HalfFullServiceImpl extends ServiceImpl<HalfFullMapper, HalfFull> implements HalfFullService {
    @Override
    public IPage<HalfFull> getHalfFullList(Integer page, Integer pageSize, Integer isTodayData) {
        LambdaQueryWrapper<HalfFull> wrapper = new LambdaQueryWrapper<>();
        if (isTodayData != null) wrapper.eq(HalfFull::getIsTodayData, isTodayData);
        wrapper.orderByDesc(HalfFull::getMatchDate);
        return page(new Page<>(page, pageSize), wrapper);
    }
}
