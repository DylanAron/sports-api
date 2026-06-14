CREATE TABLE IF NOT EXISTS `t_tab_guide` (
  `id`          int          NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `tab_key`     varchar(32)  NOT NULL COMMENT 'tab标识: home/analysis/score',
  `image_url`   varchar(500) NOT NULL COMMENT '引导图片URL',
  `status`      tinyint      NOT NULL DEFAULT 1 COMMENT '状态 0=禁用 1=启用',
  `is_global_enabled` tinyint NOT NULL DEFAULT 0 COMMENT '总开关 0=关闭 1=开启',
  `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tab_key` (`tab_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tab引导图配置表';
