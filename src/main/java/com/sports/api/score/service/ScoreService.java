package com.sports.api.score.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sports.api.score.entity.Score;

public interface ScoreService extends IService<Score> {
    IPage<Score> getScoreList(Integer page, Integer pageSize, Integer isTodayData);
}
