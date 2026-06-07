-- 分析数据表
CREATE TABLE IF NOT EXISTS `t_analysis` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `league_name` varchar(100) DEFAULT NULL COMMENT '联赛名称',
    `league_logo` varchar(500) DEFAULT NULL COMMENT '联赛logo',
    `home_name` varchar(100) NOT NULL COMMENT '主队名称',
    `home_logo` varchar(500) DEFAULT NULL COMMENT '主队logo',
    `away_name` varchar(100) NOT NULL COMMENT '客队名称',
    `away_logo` varchar(500) DEFAULT NULL COMMENT '客队logo',
    `recommend_content` varchar(500) DEFAULT NULL COMMENT '推荐分析内容',
    `is_today_data` tinyint DEFAULT '0' COMMENT '是否今日数据 1:是 0:否',
    `is_hit` tinyint DEFAULT '0' COMMENT '是否命中 1:命中 0:未命中',
    `match_date` datetime DEFAULT NULL COMMENT '赛事时间',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_match_date` (`match_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分析数据表';

-- 插入分析测试数据（按赛事时间倒序，8条数据）
INSERT INTO `t_analysis` (`league_name`, `league_logo`, `home_name`, `home_logo`, `away_name`, `away_logo`, `recommend_content`, `is_today_data`, `is_hit`, `match_date`) VALUES
('英超', NULL, '曼城', NULL, '利物浦', NULL, '曼城主场进攻火力强劲，近6场场均进球2.5个。利物浦客场防守存在隐患，预计总进球数超过2.5球。推荐：大球', 1, 1, DATE_ADD(NOW(), INTERVAL -1 DAY)),
('西甲', NULL, '皇马', NULL, '巴萨', NULL, '国家德比，皇马主场优势明显，本泽马状态火热。巴萨近期客场表现不稳，看好皇马主场不败。推荐：主胜', 1, 0, DATE_ADD(NOW(), INTERVAL -2 DAY)),
('德甲', NULL, '拜仁', NULL, '多特蒙德', NULL, '拜仁主场统治力十足，穆西亚拉领衔中场。多特防守反击效率高，但拜仁整体实力碾压。推荐：主胜', 1, 1, DATE_ADD(NOW(), INTERVAL -3 DAY)),
('法甲', NULL, '巴黎', NULL, '马赛', NULL, '巴黎攻击线豪华，姆巴佩与内马尔配合默契。马赛客场作战能力有限。推荐：主队大胜', 0, 1, DATE_ADD(NOW(), INTERVAL -4 DAY)),
('意甲', NULL, '尤文', NULL, 'AC米兰', NULL, '尤文防守稳健，主场仅失0.8球/场。AC米兰进攻端状态起伏，看好小球。推荐：小2.5球', 0, 0, DATE_ADD(NOW(), INTERVAL -5 DAY)),
('英超', NULL, '阿森纳', NULL, '切尔西', NULL, '阿森纳主场强势，年轻球员活力十足。切尔西周中刚踢完欧战，体能存疑。推荐：主胜', 0, 1, DATE_ADD(NOW(), INTERVAL -6 DAY)),
('英超', NULL, '曼联', NULL, '热刺', NULL, '曼联主场表现回升，拉什福德找回状态。热刺客场成绩一般，防守漏洞较多。推荐：主队不败', 0, 1, DATE_ADD(NOW(), INTERVAL -7 DAY)),
('西甲', NULL, '马竞', NULL, '塞维利亚', NULL, '马竞主场防守固若金汤，西蒙尼的球队在主场极具韧性。塞维利亚客场进攻乏力。推荐：小2.5球', 0, 0, DATE_ADD(NOW(), INTERVAL -8 DAY));
