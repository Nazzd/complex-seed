package com.nazzd.complex.seed.modules.auth.service;

import com.nazzd.complex.seed.modules.auth.po.Role;

import java.util.List;

public interface RoleService {

    List<Role> queryUserRoles(Long userId);
}
