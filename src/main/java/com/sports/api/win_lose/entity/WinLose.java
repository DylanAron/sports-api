package com.sports.api.win_lose.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("t_win_lose")
public class WinLose {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String leagueName;
    private String leagueLogo;
    private String homeName;
    private String homeLogo;
    private String awayLogo;
    private String awayName;
    private String recommendContent;
    private String result;
    private Integer isHit;
    private Integer isTodayData;
    private LocalDateTime matchDate;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
