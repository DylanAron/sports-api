CREATE TABLE IF NOT EXISTS `t_banner` (
  `id`          int          NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `title`       varchar(100) NOT NULL COMMENT '标题',
  `image_url`   varchar(500) NOT NULL COMMENT '图片URL',
  `sort_order`  int          NOT NULL DEFAULT 0 COMMENT '排序（升序）',
  `jump_type`   tinyint      NOT NULL DEFAULT 1 COMMENT '跳转类型 1=弹窗HTML 2=客服',
  `jump_content` longtext    NULL COMMENT '弹窗HTML内容（jump_type=1时使用）',
  `status`      tinyint      NOT NULL DEFAULT 1 COMMENT '状态 0=禁用 1=启用',
  `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_sort` (`sort_order`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='轮播图表';
