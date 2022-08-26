package com.nazzd.complex.seed.modules.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nazzd.complex.seed.modules.auth.po.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
