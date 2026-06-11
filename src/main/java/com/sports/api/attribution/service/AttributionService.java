package com.sports.api.attribution.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sports.api.attribution.entity.Attribution;

import java.util.Map;

public interface AttributionService extends IService<Attribution> {

    /**
     * 处理百度归因回调
     * @param params 百度回调参数（包含 signature、oaid、channel_id 等）
     * @return true=签名验证通过且处理成功，false=验证失败
     */
    boolean handleCallback(Map<String, String> params);
}
