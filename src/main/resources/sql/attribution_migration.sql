-- 百度归因表字段扩充迁移脚本
-- 在已有 t_attribution 表基础上追加百度回调标准参数字段
-- 执行前请确认表已存在

-- 1. 百度回调标准参数
ALTER TABLE `t_attribution`
    ADD COLUMN `aid`               varchar(100) DEFAULT NULL COMMENT '创意ID（aid）' AFTER `id`,
    ADD COLUMN `pid`               varchar(100) DEFAULT NULL COMMENT '计划ID（pid）' AFTER `aid`,
    ADD COLUMN `uid`               varchar(100) DEFAULT NULL COMMENT '单元ID（uid）' AFTER `pid`,
    ADD COLUMN `userid`            varchar(100) DEFAULT NULL COMMENT '账户ID（userid）' AFTER `uid`,
    ADD COLUMN `click_id`          varchar(255) DEFAULT NULL COMMENT '点击曝光唯一标识（click_id）' AFTER `oaid_md5`,
    ADD COLUMN `comb_id`           varchar(100) DEFAULT NULL COMMENT '程序化创意组合ID（comb_id）' AFTER `click_id`;

-- 2. 设备标识扩展
ALTER TABLE `t_attribution`
    ADD COLUMN `oaid_md5`          varchar(255) DEFAULT NULL COMMENT 'OAID的MD5值' AFTER `oaid`,
    ADD COLUMN `imei_md5`          varchar(255) DEFAULT NULL COMMENT 'IMEI的MD5值' AFTER `imei`,
    ADD COLUMN `android_id_md5`    varchar(255) DEFAULT NULL COMMENT 'Android ID的MD5值' AFTER `android_id`,
    ADD COLUMN `mac`               varchar(100) DEFAULT NULL COMMENT 'MAC地址' AFTER `android_id_md5`,
    ADD COLUMN `mac_md5`           varchar(255) DEFAULT NULL COMMENT 'MAC地址的MD5值' AFTER `mac`,
    ADD COLUMN `idfa`              varchar(255) DEFAULT NULL COMMENT 'iOS设备标识（IDFA）' AFTER `mac_md5`;

-- 3. 设备信息
ALTER TABLE `t_attribution`
    ADD COLUMN `ip`                varchar(50)  DEFAULT NULL COMMENT 'IP地址' AFTER `idfa`,
    ADD COLUMN `ua`                varchar(500) DEFAULT NULL COMMENT 'User Agent终端设备信息' AFTER `ip`,
    ADD COLUMN `os`                tinyint      DEFAULT NULL COMMENT '操作系统 1:iOS 2:Android' AFTER `ua`,
    ADD COLUMN `size`              varchar(50)  DEFAULT NULL COMMENT '屏幕尺寸（size）' AFTER `os`;

-- 4. 百度特有标识
ALTER TABLE `t_attribution`
    ADD COLUMN `ts`                bigint       DEFAULT NULL COMMENT '百度回调时间戳（ts）' AFTER `callback_time`,
    ADD COLUMN `bd_vid`            varchar(255) DEFAULT NULL COMMENT '百度点击曝光唯一标识（bd_vid）' AFTER `ts`,
    ADD COLUMN `deeplink_url`      varchar(500) DEFAULT NULL COMMENT '调起URL（deeplink_url）' AFTER `bd_vid`,
    ADD COLUMN `ext_info`          varchar(500) DEFAULT NULL COMMENT '广告信息透传字段（ext_info）' AFTER `deeplink_url`;

-- 5. 新增索引
ALTER TABLE `t_attribution`
    ADD KEY `idx_imei` (`imei`),
    ADD KEY `idx_click_id` (`click_id`),
    ADD KEY `idx_bd_vid` (`bd_vid`);
