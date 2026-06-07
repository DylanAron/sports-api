package com.sports.api.analysis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_analysis")
public class Analysis {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String leagueLogo;

    private String leagueName;

    private String homeLogo;

    private String homeName;

    private String awayLogo;

    private String awayName;

    private LocalDateTime matchTime;

    private String scoreResult;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
