package com.nazzd.complex.seed.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nazzd.complex.seed.bo.UserAdd;
import com.nazzd.complex.seed.po.User;

public interface UserService {

    void addUser(UserAdd userAdd);

    IPage<User> queryUserPage(String userName, Page<User> page);
}
