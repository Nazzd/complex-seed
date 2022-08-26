package com.nazzd.complex.seed.modules.auth.controller;

import com.nazzd.complex.seed.cache.CaffeineCache;
import com.nazzd.complex.seed.config.security.AccessToken;
import com.nazzd.complex.seed.config.security.JwtProvider;
import com.nazzd.complex.seed.config.security.UserDetail;
import com.nazzd.complex.seed.modules.auth.bo.LoginReq;
import com.nazzd.complex.seed.modules.auth.enums.CacheName;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
@Api(tags = "登录")
@Slf4j
public class LoginController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private CaffeineCache caffeineCache;

    @Resource
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public AccessToken login(@RequestBody LoginReq loginReq) {
        log.debug("进入login方法");
        // 1 创建UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(
                loginReq.getUsername(), loginReq.getPassword());
        // 2 认证
        Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication);
        // 3 保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 4 生成自定义token
        AccessToken accessToken = jwtProvider.createToken((UserDetails) authentication.getPrincipal());

        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        // 放入缓存
        caffeineCache.put(CacheName.USER, userDetail.getUsername(), userDetail);
        return accessToken;
    }
}
