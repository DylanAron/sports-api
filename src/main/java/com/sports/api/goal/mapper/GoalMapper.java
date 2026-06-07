package com.sports.api.goal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sports.api.goal.entity.Goal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoalMapper extends BaseMapper<Goal> {}
