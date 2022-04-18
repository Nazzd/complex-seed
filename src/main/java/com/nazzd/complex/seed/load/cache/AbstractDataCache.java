package com.nazzd.complex.seed.load.cache;

import cn.hutool.core.collection.CollectionUtil;
import com.nazzd.complex.seed.load.entity.AbstractCacheEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public abstract class AbstractDataCache<T extends AbstractCacheEntity> {

    protected Map<String, T> cache = new ConcurrentHashMap<>();

    public synchronized boolean processFullCache(List<T> messageList) {
        log.info("precessFullCache, message size :{}", messageList.size());
        cache.clear();
        if (!CollectionUtil.isEmpty(messageList)) {
            messageList.forEach(message -> cache.put(message.geyCacheKey(), message));
        }
        return true;
    }

    public synchronized boolean processIncrCache(List<T> messageList) {
        log.info("precessFullCache, message size :{}", messageList.size());
        if (!CollectionUtil.isEmpty(messageList)) {
            messageList.forEach(message -> cache.put(message.geyCacheKey(), message));
        }
        return true;
    }

    public T getCacheById(String cacheKey) {
        return cache.get(cacheKey);
    }
}
