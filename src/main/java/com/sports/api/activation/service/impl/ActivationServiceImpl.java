package com.sports.api.activation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sports.api.activation.dto.ActivationReportRequest;
import com.sports.api.activation.entity.AppActivation;
import com.sports.api.activation.mapper.AppActivationMapper;
import com.sports.api.activation.service.ActivationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ActivationServiceImpl extends ServiceImpl<AppActivationMapper, AppActivation>
        implements ActivationService {

    @Override
    public void report(ActivationReportRequest request) {
        AppActivation activation = new AppActivation();
        activation.setDeviceId(request.getDeviceId());
        activation.setMarketId(request.getMarketId() != null ? request.getMarketId() : 1);
        activation.setPackageId(request.getPackageId());
        activation.setReportTime(LocalDateTime.now());

        try {
            baseMapper.insert(activation);
            log.info("Activation recorded: deviceId={}, marketId={}, packageId={}",
                    request.getDeviceId(), activation.getMarketId(), request.getPackageId());
        } catch (DuplicateKeyException e) {
            log.info("Duplicate activation ignored: deviceId={}", request.getDeviceId());
        }
    }
}
