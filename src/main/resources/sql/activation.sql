-- App激活记录表
CREATE TABLE IF NOT EXISTS `t_app_activation` (
    `id`          int          NOT NULL AUTO_INCREMENT COMMENT '主键自增',
    `device_id`   varchar(128) NOT NULL COMMENT '设备ID (ANDROID_ID / IDFV)',
    `market_id`   tinyint      NOT NULL DEFAULT 1 COMMENT '市场ID: 1=百度 2=华为 3=小米 4=应用宝 5=Oppo 6=Vivo 7=360 99=其他',
    `package_id`  varchar(64)  NOT NULL COMMENT '渠道包ID（16位随机字母数字，每个版本固定）',
    `report_time` datetime     NOT NULL COMMENT '激活上报时间',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_device_id` (`device_id`) COMMENT '设备ID唯一索引（用于去重）',
    KEY `idx_report_time` (`report_time`) COMMENT '上报时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='App激活记录表';
