package com.nazzd.complex.seed.modules.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nazzd.complex.seed.modules.auth.bo.UserAddAndUpdate;
import com.nazzd.complex.seed.modules.auth.convert.UserAddConvert;
import com.nazzd.complex.seed.modules.auth.mapper.UserMapper;
import com.nazzd.complex.seed.modules.auth.po.User;
import com.nazzd.complex.seed.modules.auth.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserAddConvert userAddConvert;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserAddAndUpdate userAddAndUpdate) {
        User user = userAddConvert.toPo(userAddAndUpdate);
        userMapper.insert(user);
    }

    @Override
    public IPage<User> queryUserPage(String userName, Page<User> page) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().like(Objects.nonNull(userName), User::getName, userName);
        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    public User getUser(Long userId) {
        return userMapper.selectById(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(Long userId, UserAddAndUpdate userAddAndUpdate) {
        LambdaUpdateWrapper<User> updateWrapper = Wrappers.<User>lambdaUpdate()
                .set(Strings.isNotEmpty(userAddAndUpdate.getName()), User::getName, userAddAndUpdate.getName())
                .set(Strings.isNotEmpty(userAddAndUpdate.getDescription()), User::getDescription, userAddAndUpdate.getDescription())
                .set(Objects.nonNull(userAddAndUpdate.getIsEnable()), User::getIsEnable, userAddAndUpdate.getIsEnable())
                .eq(User::getId, userId);
        userMapper.update(null, updateWrapper);
    }

    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getUsername, username);
        return userMapper.selectOne(queryWrapper);
    }
}
