package com.nazzd.complex.seed.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nazzd.complex.seed.load.LoadDataEnum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomMapper extends BaseMapper<Object> {

    List<Map<String, Object>> queryMaps(LoadDataEnum loadDataEnum);
}
