-- 百度归因记录表
-- 用于存储百度 AppTrack 广告归因回调数据，追踪各广告渠道的推广效果
-- 参照百度回调参数字段设计
CREATE TABLE IF NOT EXISTS `t_attribution` (
    `id`                bigint       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 百度回调标准参数
    `aid`               varchar(100) DEFAULT NULL COMMENT '创意ID（aid）',
    `pid`               varchar(100) DEFAULT NULL COMMENT '计划ID（pid）',
    `uid`               varchar(100) DEFAULT NULL COMMENT '单元ID（uid）',
    `userid`            varchar(100) DEFAULT NULL COMMENT '账户ID（userid）',
    `click_id`          varchar(255) DEFAULT NULL COMMENT '点击曝光唯一标识（click_id）',
    `comb_id`           varchar(100) DEFAULT NULL COMMENT '程序化创意组合ID（comb_id）',
    -- 设备标识
    `oaid`              varchar(255) DEFAULT NULL COMMENT 'Android广告标识（OAID）',
    `oaid_md5`          varchar(255) DEFAULT NULL COMMENT 'OAID的MD5值',
    `imei`              varchar(100) DEFAULT NULL COMMENT 'IMEI设备标识',
    `imei_md5`          varchar(255) DEFAULT NULL COMMENT 'IMEI的MD5值',
    `android_id`        varchar(255) DEFAULT NULL COMMENT 'Android设备标识（android_id）',
    `android_id_md5`    varchar(255) DEFAULT NULL COMMENT 'Android ID的MD5值',
    `mac`               varchar(100) DEFAULT NULL COMMENT 'MAC地址',
    `mac_md5`           varchar(255) DEFAULT NULL COMMENT 'MAC地址的MD5值',
    `idfa`              varchar(255) DEFAULT NULL COMMENT 'iOS设备标识（IDFA）',
    -- 设备信息
    `ip`                varchar(50)  DEFAULT NULL COMMENT 'IP地址',
    `ua`                varchar(500) DEFAULT NULL COMMENT 'User Agent终端设备信息',
    `os`                tinyint      DEFAULT NULL COMMENT '操作系统 1:iOS 2:Android',
    `size`              varchar(50)  DEFAULT NULL COMMENT '屏幕尺寸（size）',
    -- 广告渠道信息
    `channel_id`        varchar(100) DEFAULT NULL COMMENT '渠道ID，标识广告投放渠道',
    `campaign_id`       varchar(100) DEFAULT NULL COMMENT '广告计划ID',
    `ad_id`             varchar(100) DEFAULT NULL COMMENT '广告ID',
    `ad_name`           varchar(255) DEFAULT NULL COMMENT '广告名称',
    -- 转化信息
    `callback_type`     tinyint      DEFAULT '0' COMMENT '归因回调类型 0:激活 1:注册 2:付费 3:次日留存',
    `callback_data`     json         DEFAULT NULL COMMENT '百度回调的完整原始JSON数据',
    `callback_time`     datetime     DEFAULT NULL COMMENT '百度回调时间',
    `ts`                bigint       DEFAULT NULL COMMENT '百度回调时间戳（ts）',
    `bd_vid`            varchar(255) DEFAULT NULL COMMENT '百度点击曝光唯一标识（bd_vid）',
    `deeplink_url`      varchar(500) DEFAULT NULL COMMENT '调起URL（deeplink_url）',
    `ext_info`          varchar(500) DEFAULT NULL COMMENT '广告信息透传字段（ext_info）',
    -- 客户端上报信息
    `app_active_time`   datetime     DEFAULT NULL COMMENT '应用首次激活时间（客户端上报）',
    -- 状态
    `status`            tinyint      DEFAULT '0' COMMENT '处理状态 0:待处理 1:已处理 2:已忽略(重复回调)',
    `remark`            varchar(500) DEFAULT NULL COMMENT '备注',
    `create_time`       datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    `update_time`       datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_oaid` (`oaid`),
    KEY `idx_android_id` (`android_id`),
    KEY `idx_imei` (`imei`),
    KEY `idx_channel_id` (`channel_id`),
    KEY `idx_callback_time` (`callback_time`),
    KEY `idx_click_id` (`click_id`),
    KEY `idx_bd_vid` (`bd_vid`),
    KEY `idx_oaid_type` (`oaid`, `callback_type`),
    KEY `idx_callback_unique` (`oaid`, `callback_type`, `callback_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='百度AppTrack归因记录表';
