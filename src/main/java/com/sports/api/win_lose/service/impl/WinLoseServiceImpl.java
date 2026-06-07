package com.sports.api.win_lose.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sports.api.win_lose.entity.WinLose;
import com.sports.api.win_lose.mapper.WinLoseMapper;
import com.sports.api.win_lose.service.WinLoseService;
import org.springframework.stereotype.Service;

@Service
public class WinLoseServiceImpl extends ServiceImpl<WinLoseMapper, WinLose> implements WinLoseService {
    @Override
    public IPage<WinLose> getWinLoseList(Integer page, Integer pageSize, Integer isTodayData) {
        LambdaQueryWrapper<WinLose> wrapper = new LambdaQueryWrapper<>();
        if (isTodayData != null) wrapper.eq(WinLose::getIsTodayData, isTodayData);
        wrapper.orderByDesc(WinLose::getMatchDate);
        return page(new Page<>(page, pageSize), wrapper);
    }
}
