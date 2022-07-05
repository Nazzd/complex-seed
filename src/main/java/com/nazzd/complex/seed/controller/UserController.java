package com.nazzd.complex.seed.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nazzd.complex.seed.bo.UserAddAndUpdate;
import com.nazzd.complex.seed.exception.ClientException;
import com.nazzd.complex.seed.po.User;
import com.nazzd.complex.seed.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/system/user")
@Api(tags = "系统管理-用户管理", value = "用户管理")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("新增用户")
    @PostMapping
    public void addUser(@RequestBody @Validated @ApiParam(value = "用户新增对象", required = true) UserAddAndUpdate userAddAndUpdate) {
        userService.addUser(userAddAndUpdate);
    }

    @GetMapping("/page")
    public IPage<User> queryUserPage(@RequestParam("pageSize") Integer pageSize,
                                     @RequestParam("pageNum") Integer pageNum,
                                     @RequestParam(value = "userName", required = false) String userName) {

        Page<User> page = new Page<>(pageNum, pageSize);
        return userService.queryUserPage(userName, page);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody UserAddAndUpdate userAddAndUpdate) {
        User user = userService.getUser(userId);
        if (Objects.isNull(user)) {
            throw new ClientException("该用户不存在!");
        }
        userService.updateUser(userId, userAddAndUpdate);
    }
}
