package com.nazzd.complex.seed.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_source")
public class Source {

    private Long id;

    private String name;

    private Integer depth;

    private String path;

    private String icon;

    private String url;

    private Long parentId;

    private Boolean isDelete;

    private Long createTime;

    private Long updateTime;
}
