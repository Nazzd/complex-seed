package com.nazzd.complex.seed.modules.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nazzd.complex.seed.modules.auth.po.Role;
import com.nazzd.complex.seed.modules.auth.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Role> queryUserRoles(Long userId);
}
