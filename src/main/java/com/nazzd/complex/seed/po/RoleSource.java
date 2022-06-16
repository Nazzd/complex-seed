package com.nazzd.complex.seed.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_role_source")
public class RoleSource {

    private Long id;

    private Long roleId;

    private Long sourceId;

}
