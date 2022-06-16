package com.nazzd.complex.seed.service.impl;

import com.nazzd.complex.seed.mapper.RoleMapper;
import com.nazzd.complex.seed.mapper.SourceMapper;
import com.nazzd.complex.seed.mapper.UserMapper;
import com.nazzd.complex.seed.service.SourceService;
import com.nazzd.complex.seed.service.UserService;
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
