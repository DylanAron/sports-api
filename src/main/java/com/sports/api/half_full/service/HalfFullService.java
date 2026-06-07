package com.sports.api.half_full.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sports.api.half_full.entity.HalfFull;

public interface HalfFullService extends IService<HalfFull> {
    IPage<HalfFull> getHalfFullList(Integer page, Integer pageSize, Integer isTodayData);
}
