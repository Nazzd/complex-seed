package com.nazzd.complex.seed.modules.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomMapper extends BaseMapper<Object> {

    List<Map<String, Object>> queryMaps(String sql);

    int healthCheck();
}
