package com.sports.api.win_lose.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sports.api.win_lose.entity.WinLose;

public interface WinLoseService extends IService<WinLose> {
    IPage<WinLose> getWinLoseList(Integer page, Integer pageSize, Integer isTodayData);
}
