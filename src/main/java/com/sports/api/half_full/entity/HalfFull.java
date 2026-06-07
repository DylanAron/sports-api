package com.sports.api.half_full.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("t_half_full")
public class HalfFull {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String leagueName;
    private String leagueLogo;
    private String homeName;
    private String homeLogo;
    private String awayLogo;
    private String awayName;
    private String halfScore;
    private String fullScore;
    private String recommendContent;
    private Integer isTodayData;
    private Integer isHit;
    private LocalDateTime matchDate;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
