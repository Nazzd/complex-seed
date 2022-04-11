package com.nazzd.complex.seed.load;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface CustomBaseMapper<T extends AbstractResult> extends BaseMapper<T> {

    List<T> loadData();
}
