package com.nazzd.complex.seed.load.cache;

import cn.hutool.core.bean.BeanUtil;
import com.nazzd.complex.seed.load.LoadCacheFactory;
import com.nazzd.complex.seed.load.entity.Demo;
import com.nazzd.complex.seed.load.enums.CacheLoadEnum;
import com.nazzd.complex.seed.load.source.ISource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DemoCache extends AbstractLoadSource {

    /**
     * 自己定义缓存结构
     */
    public static final Map<String, Demo> CACHE = new ConcurrentHashMap<>();


    protected DemoCache(ISource iSource) {
        super(iSource);
    }

    @Override
    public CacheLoadEnum getLoadEnum() {
        return CacheLoadEnum.DEMO_CACHE;
    }

    @Override
    public void storeCache(List<Map<String, Object>> dataList, boolean isIncr) {
        CacheLoadEnum loadEnum = this.getLoadEnum();
        // 如果是全量更新,需要清空缓存
        if (!isIncr) {
            CACHE.clear();
        }
        dataList.forEach(data -> {
            Demo demo = new Demo();
            // 缓存唯一值
            String key = String.valueOf(data.get(loadEnum.getPrimaryKey()));
            //反序列化
            BeanUtil.fillBeanWithMap(data, demo, true);
            CACHE.put(key, demo);
        });
        // 更新增量同步的时间戳
        LoadCacheFactory.CURRENT_SYNC_TIMESTAMP.put(loadEnum.getCacheKey(), LocalDateTime.now());
    }
}
