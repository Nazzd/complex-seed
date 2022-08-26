package com.nazzd.complex.seed.config.security;

import com.nazzd.complex.seed.modules.auth.mapper.RoleMapper;
import com.nazzd.complex.seed.modules.auth.mapper.UserMapper;
import com.nazzd.complex.seed.modules.auth.po.Role;
import com.nazzd.complex.seed.modules.auth.po.User;
import com.nazzd.complex.seed.modules.auth.service.RoleService;
import com.nazzd.complex.seed.modules.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("开始登陆验证，用户名为: {}", s);
        // 根据用户名验证用户
        User user = userService.getUserByUsername(s);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名不存在，登陆失败。");
        }
        // 构建UserDetail对象
        UserDetail userDetail = new UserDetail();
        userDetail.setUserInfo(user);

        List<Role> roles = roleService.queryUserRoles(user.getId());
        userDetail.setRoleInfoList(roles);
        return userDetail;
    }
}
