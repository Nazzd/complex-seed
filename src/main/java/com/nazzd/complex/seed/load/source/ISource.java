package com.nazzd.complex.seed.load.source;

import com.nazzd.complex.seed.load.enums.CacheLoadEnum;

import java.util.List;
import java.util.Map;

public interface ISource {

    List<Map<String,Object>> fullLoad(CacheLoadEnum cacheLoadEnum);


    List<Map<String,Object>> incrLoad(CacheLoadEnum cacheLoadEnum);
}
