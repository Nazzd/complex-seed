package com.nazzd.complex.seed.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nazzd.complex.seed.bo.UserAddAndUpdate;
import com.nazzd.complex.seed.po.User;

public interface UserService {

    void addUser(UserAddAndUpdate userAddAndUpdate);

    IPage<User> queryUserPage(String userName, Page<User> page);

    User getUser(Long userId);

    void updateUser(Long userId, UserAddAndUpdate userAddAndUpdate);
}
