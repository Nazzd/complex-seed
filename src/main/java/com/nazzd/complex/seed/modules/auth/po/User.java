package com.nazzd.complex.seed.modules.auth.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("t_user")
@Data
public class User {

    private Long id;

    private String username;

    private String phoneNum;

    private String password;

    private String name;

    private Integer age;

    private String description;

    private Boolean isEnable;

    private Boolean isDelete;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
