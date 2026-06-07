package com.sports.api.intelligence.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sports.api.intelligence.entity.Intelligence;
import java.util.List;

public interface IntelligenceService extends IService<Intelligence> {
    List<Intelligence> getRecentWeek();
}
