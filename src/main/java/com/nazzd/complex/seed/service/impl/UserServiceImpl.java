package com.nazzd.complex.seed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nazzd.complex.seed.bo.UserAdd;
import com.nazzd.complex.seed.convert.UserAddConvert;
import com.nazzd.complex.seed.mapper.RoleMapper;
import com.nazzd.complex.seed.mapper.SourceMapper;
import com.nazzd.complex.seed.mapper.UserMapper;
import com.nazzd.complex.seed.po.User;
import com.nazzd.complex.seed.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private SourceMapper sourceMapper;

    @Resource
    private UserAddConvert userAddConvert;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserAdd userAdd) {
        User user = userAddConvert.toPo(userAdd);
        userMapper.insert(user);
    }

    @Override
    public IPage<User> queryUserPage(String userName, Page<User> page) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().like(Objects::nonNull, userName);
        return userMapper.selectPage(page, queryWrapper);
    }
}
