package com.sports.api.goal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sports.api.goal.entity.Goal;

public interface GoalService extends IService<Goal> {
    IPage<Goal> getGoalList(Integer page, Integer pageSize, Integer isTodayData);
}
