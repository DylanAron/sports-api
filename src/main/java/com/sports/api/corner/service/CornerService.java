package com.sports.api.corner.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sports.api.corner.entity.Corner;

public interface CornerService extends IService<Corner> {
    IPage<Corner> getCornerList(Integer page, Integer pageSize, Integer isTodayData);
    Corner getCornerDetail(Long id);
}
