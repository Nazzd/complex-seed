package com.nazzd.complex.seed.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nazzd.complex.seed.bo.UserAdd;
import com.nazzd.complex.seed.po.User;
import com.nazzd.complex.seed.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    public void addUser(@RequestBody @Validated UserAdd userAdd) {
        userService.addUser(userAdd);
    }

    @GetMapping("/page")
    public IPage<User> queryUserPage(@RequestParam("pageSize") Integer pageSize,
                                     @RequestParam("pageNum") Integer pageNum,
                                     @RequestParam(value = "userName", required = false) String userName) {

        Page<User> page = new Page<>(pageNum, pageSize);
        return userService.queryUserPage(userName, page);
    }
}
