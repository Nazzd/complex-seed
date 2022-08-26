package com.nazzd.complex.seed.modules.auth.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_user_role")
public class UserRole {

    private Long id;

    private Long userId;

    private Long roleId;
}
