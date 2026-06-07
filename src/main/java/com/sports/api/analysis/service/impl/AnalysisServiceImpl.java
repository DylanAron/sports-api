package com.sports.api.analysis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sports.api.analysis.entity.Analysis;
import com.sports.api.analysis.mapper.AnalysisMapper;
import com.sports.api.analysis.service.AnalysisService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisServiceImpl extends ServiceImpl<AnalysisMapper, Analysis> implements AnalysisService {

    @Override
    public List<Analysis> getRecentAnalysis() {
        LambdaQueryWrapper<Analysis> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Analysis::getMatchTime);
        wrapper.last("LIMIT 8");
        return list(wrapper);
    }
}
