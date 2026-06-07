package com.sports.api.intelligence.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("t_intelligence")
public class Intelligence {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private LocalDate intelDate;
    private String content;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
