package com.nazzd.complex.seed.modules.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.nazzd.complex.seed.modules.auth.mapper.RoleMapper;
import com.nazzd.complex.seed.modules.auth.mapper.SourceMapper;
import com.nazzd.complex.seed.modules.auth.mapper.UserMapper;
import com.nazzd.complex.seed.modules.auth.mapper.UserRoleMapper;
import com.nazzd.complex.seed.modules.auth.po.Role;
import com.nazzd.complex.seed.modules.auth.po.UserRole;
import com.nazzd.complex.seed.modules.auth.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private SourceMapper sourceMapper;

    public List<Role> queryUserRoles(Long userId) {
       return userRoleMapper.queryUserRoles(userId);
    }

}
