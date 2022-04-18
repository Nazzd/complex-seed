package com.nazzd.complex.seed.load.source;

import com.nazzd.complex.seed.load.LoadCacheFactory;
import com.nazzd.complex.seed.load.enums.CacheLoadEnum;
import com.nazzd.complex.seed.service.LoadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Primary
@Slf4j
@Component
public class DatabaseSource implements ISource {

    @Resource
    private LoadService loadService;

    @Override
    public List<Map<String, Object>> fullLoad(CacheLoadEnum cacheLoadEnum) {
        log.info("start execute sql:{}", cacheLoadEnum.getSql());
        List<Map<String, Object>> resultMapList = Lists.newArrayList();
        if (LoadCacheFactory.IS_USABLE) {
            // 从数据库获取数据
            resultMapList = loadService.queryMaps(cacheLoadEnum.getSql());
        }
        return resultMapList;
    }

    @Override
    public List<Map<String, Object>> incrLoad(CacheLoadEnum cacheLoadEnum) {
        // 获取当前缓存的最近更新时间
        LocalDateTime localDateTime = LoadCacheFactory.CURRENT_SYNC_TIMESTAMP.get(cacheLoadEnum.getCacheKey());
        // 组装增量更新的sql
        List<Map<String, Object>> resultMapList = Lists.newArrayList();
        if (LoadCacheFactory.IS_USABLE) {
            // 从数据库获取数据
            resultMapList = loadService.queryMaps(cacheLoadEnum.getSql());
        }
        return resultMapList;
    }
}
