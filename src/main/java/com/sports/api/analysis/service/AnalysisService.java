package com.sports.api.analysis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sports.api.analysis.entity.Analysis;

import java.util.List;

public interface AnalysisService extends IService<Analysis> {
    List<Analysis> getRecentAnalysis();
}
