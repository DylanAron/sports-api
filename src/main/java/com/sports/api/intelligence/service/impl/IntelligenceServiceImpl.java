package com.sports.api.intelligence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sports.api.intelligence.entity.Intelligence;
import com.sports.api.intelligence.mapper.IntelligenceMapper;
import com.sports.api.intelligence.service.IntelligenceService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class IntelligenceServiceImpl extends ServiceImpl<IntelligenceMapper, Intelligence> implements IntelligenceService {
    @Override
    public List<Intelligence> getRecentWeek() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        return list(new LambdaQueryWrapper<Intelligence>()
                .ge(Intelligence::getIntelDate, sevenDaysAgo)
                .orderByDesc(Intelligence::getIntelDate));
    }
}
