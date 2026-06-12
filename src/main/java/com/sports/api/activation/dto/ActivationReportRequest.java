package com.sports.api.activation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ActivationReportRequest {

    @NotBlank(message = "设备ID不能为空")
    private String deviceId;

    private Integer marketId = 1;

    @NotBlank(message = "包ID不能为空")
    private String packageId;
}
