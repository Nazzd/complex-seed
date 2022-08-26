package com.nazzd.complex.seed.modules.auth.controller;

import com.nazzd.complex.seed.modules.auth.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/source")
@Api(tags = "系统管理-资源管理", value = "资源管理")
public class SourceController {

    @Resource
    private UserService userService;

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }


}
