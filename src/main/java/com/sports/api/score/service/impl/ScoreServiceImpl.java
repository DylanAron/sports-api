package com.sports.api.score.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sports.api.score.entity.Score;
import com.sports.api.score.mapper.ScoreMapper;
import com.sports.api.score.service.ScoreService;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {
    @Override
    public IPage<Score> getScoreList(Integer page, Integer pageSize, Integer isTodayData) {
        LambdaQueryWrapper<Score> wrapper = new LambdaQueryWrapper<>();
        if (isTodayData != null) wrapper.eq(Score::getIsTodayData, isTodayData);
        wrapper.orderByDesc(Score::getMatchDate);
        return page(new Page<>(page, pageSize), wrapper);
    }
}
