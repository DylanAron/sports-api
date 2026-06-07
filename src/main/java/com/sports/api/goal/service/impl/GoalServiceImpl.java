package com.sports.api.goal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sports.api.goal.entity.Goal;
import com.sports.api.goal.mapper.GoalMapper;
import com.sports.api.goal.service.GoalService;
import org.springframework.stereotype.Service;

@Service
public class GoalServiceImpl extends ServiceImpl<GoalMapper, Goal> implements GoalService {
    @Override
    public IPage<Goal> getGoalList(Integer page, Integer pageSize, Integer isTodayData) {
        LambdaQueryWrapper<Goal> wrapper = new LambdaQueryWrapper<>();
        if (isTodayData != null) wrapper.eq(Goal::getIsTodayData, isTodayData);
        wrapper.orderByDesc(Goal::getMatchDate);
        return page(new Page<>(page, pageSize), wrapper);
    }
}
