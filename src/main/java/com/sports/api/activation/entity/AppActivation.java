package com.sports.api.activation.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_app_activation")
public class AppActivation {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String deviceId;

    private Integer marketId;

    private String packageId;

    private LocalDateTime reportTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
