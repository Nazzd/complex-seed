package com.nazzd.complex.seed.modules.pds.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "t_pds_category")
public class Category {

    private Integer id;

    private String name;

    private Integer parentId;

    private Integer level;

    private String path;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
