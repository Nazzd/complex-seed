package com.nazzd.complex.seed.modules.auth.service.impl;

import com.nazzd.complex.seed.modules.auth.mapper.RoleMapper;
import com.nazzd.complex.seed.modules.auth.mapper.SourceMapper;
import com.nazzd.complex.seed.modules.auth.mapper.UserMapper;
import com.nazzd.complex.seed.modules.auth.service.SourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SourceServiceImpl implements SourceService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private SourceMapper sourceMapper;

}
