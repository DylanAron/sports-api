-- 创建数据库
CREATE DATABASE IF NOT EXISTS `sports-admin` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `sports-admin`;

-- 用户表
CREATE TABLE IF NOT EXISTS `t_user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `password` varchar(255) NOT NULL COMMENT '密码（加密存储）',
    `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
    `avatar` varchar(500) DEFAULT NULL COMMENT '头像URL',
    `bio` varchar(255) DEFAULT NULL COMMENT '个人介绍',
    `status` tinyint DEFAULT '1' COMMENT '状态 1:正常 0:禁用',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 角球数据表
CREATE TABLE IF NOT EXISTS `t_corner` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `match_info` varchar(255) NOT NULL COMMENT '比赛信息（如：曼城 vs 利物浦）',
    `home_team` varchar(100) NOT NULL COMMENT '主队名称',
    `away_team` varchar(100) NOT NULL COMMENT '客队名称',
    `home_logo` varchar(500) DEFAULT NULL COMMENT '主队logo',
    `away_logo` varchar(500) DEFAULT NULL COMMENT '客队logo',
    `league_name` varchar(100) DEFAULT NULL COMMENT '联赛名称',
    `match_time` datetime DEFAULT NULL COMMENT '比赛时间',
    `home_corner_avg` decimal(5,1) DEFAULT '0.0' COMMENT '主队场均角球数',
    `away_corner_avg` decimal(5,1) DEFAULT '0.0' COMMENT '客队场均角球数',
    `total_corner_avg` decimal(5,1) DEFAULT '0.0' COMMENT '场均总角球数',
    `prediction` varchar(50) DEFAULT NULL COMMENT '角球预测（如：大9.5）',
    `prediction_odds` decimal(5,2) DEFAULT NULL COMMENT '预测赔率',
    `analysis` text COMMENT '角球分析内容',
    `status` tinyint DEFAULT '1' COMMENT '状态 1:进行中 0:已结束',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_match_time` (`match_time`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角球数据表';

-- 插入角球测试数据
INSERT INTO `t_corner` (`match_info`, `home_team`, `away_team`, `home_logo`, `away_logo`, `league_name`, `match_time`, `home_corner_avg`, `away_corner_avg`, `total_corner_avg`, `prediction`, `prediction_odds`, `analysis`) VALUES
('曼城 vs 利物浦', '曼城', '利物浦', NULL, NULL, '英超', DATE_ADD(NOW(), INTERVAL 2 DAY), 6.5, 5.2, 11.7, '大10.5', 1.85, '曼城主场角球能力强劲，场均6.5个角球。利物浦客场反击犀利，场均也能获得5.2个角球。两队历史交锋角球数普遍较多，本场看好大角球。'),
('皇马 vs 巴萨', '皇马', '巴萨', NULL, NULL, '西甲', DATE_ADD(NOW(), INTERVAL 1 DAY), 5.8, 4.9, 10.7, '大9.5', 1.75, '国家德比向来激烈，皇马主场控球率高，边路进攻频繁。巴萨传控打法同样能制造大量角球机会。'),
('拜仁 vs 多特蒙德', '拜仁', '多特蒙德', NULL, NULL, '德甲', DATE_ADD(NOW(), INTERVAL 3 DAY), 7.2, 4.5, 11.7, '大10.5', 1.90, '拜仁主场压制力极强，场均7.2个角球冠绝联赛。多特蒙德反击速度快，也能获得不少角球。'),
('巴黎 vs 马赛', '巴黎', '马赛', NULL, NULL, '法甲', DATE_ADD(NOW(), INTERVAL 2 DAY), 6.1, 3.8, 9.9, '大9.5', 1.80, '巴黎主场攻击力强劲，姆巴佩领衔的锋线频频制造角球。马赛客场也有一定反击能力。'),
('尤文 vs AC米兰', '尤文', 'AC米兰', NULL, NULL, '意甲', DATE_ADD(NOW(), INTERVAL 1 DAY), 5.5, 4.2, 9.7, '小10.5', 1.70, '意甲节奏相对较慢，尤文防守稳健，AC米兰同样注重防守。两队交锋角球数相对保守。');
