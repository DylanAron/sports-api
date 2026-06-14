package com.sports.api.tabguide.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_tab_guide")
public class TabGuide {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String tabKey;
    private String imageUrl;
    private Integer status;
    private Integer isGlobalEnabled;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
