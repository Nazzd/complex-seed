package com.nazzd.complex.seed.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nazzd.complex.seed.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
