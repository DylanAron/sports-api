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

    /** OAID（开放匿名设备标识） */
    private String oaid;

    /** 客户端公网 IP */
    private String ip;

    private LocalDateTime reportTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
