package com.nazzd.complex.seed.controller;

import com.nazzd.complex.seed.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/role")
@Api(tags = "系统管理-角色管理", value = "角色管理")
public class RoleController {

    @Resource
    private UserService userService;

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }


}
