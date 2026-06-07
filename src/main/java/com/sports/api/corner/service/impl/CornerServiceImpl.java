package com.sports.api.corner.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sports.api.common.BusinessException;
import com.sports.api.corner.entity.Corner;
import com.sports.api.corner.mapper.CornerMapper;
import com.sports.api.corner.service.CornerService;
import org.springframework.stereotype.Service;

@Service
public class CornerServiceImpl extends ServiceImpl<CornerMapper, Corner> implements CornerService {

    @Override
    public IPage<Corner> getCornerList(Integer page, Integer pageSize, Integer isTodayData) {
        LambdaQueryWrapper<Corner> wrapper = new LambdaQueryWrapper<>();
        if (isTodayData != null) {
            wrapper.eq(Corner::getIsTodayData, isTodayData);
        }
        wrapper.orderByDesc(Corner::getMatchDate);
        return page(new Page<>(page, pageSize), wrapper);
    }

    @Override
    public Corner getCornerDetail(Long id) {
        Corner corner = getById(id);
        if (corner == null) {
            throw new BusinessException("角球数据不存在");
        }
        return corner;
    }
}
