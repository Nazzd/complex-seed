package com.nazzd.complex.seed.load.enums;

import com.nazzd.complex.seed.load.cache.AbstractDataCache;
import com.nazzd.complex.seed.load.cache.DemoCache;
import com.nazzd.complex.seed.load.entity.AbstractCacheEntity;
import com.nazzd.complex.seed.load.entity.DemoEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum DataCacheEnum {

    DEMO_CACHE(1, DemoEntity.class, DemoCache.class);

    private int cacheType;

    private Class<? extends AbstractCacheEntity> cacheEntityClass;

    private Class<? extends AbstractDataCache> cacheManager;


    public static final Map<Integer, DataCacheEnum> cache = new ConcurrentHashMap<>();

    public static final Map<DataCacheEnum, AbstractCacheEntity> cacheInstance = new ConcurrentHashMap<>();


    static {
        for (DataCacheEnum cacheEnum : values()) {
            cache.put(cacheEnum.getCacheType(), cacheEnum);
            try {
                cacheInstance.put(cacheEnum, cacheEnum.getCacheEntityClass().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    DataCacheEnum(int cacheType, Class<? extends AbstractCacheEntity> cacheEntityClass, Class<? extends AbstractDataCache> cacheManager) {
        this.cacheType = cacheType;
        this.cacheEntityClass = cacheEntityClass;
        this.cacheManager = cacheManager;
    }

    public int getCacheType() {
        return cacheType;
    }

    public Class<? extends AbstractCacheEntity> getCacheEntityClass() {
        return cacheEntityClass;
    }

    public Class<? extends AbstractDataCache> getCacheManager() {
        return cacheManager;
    }

    public static DataCacheEnum fromInt(int cacheType) {
        return cache.get(cacheType);
    }

    public static AbstractCacheEntity getAbstractCacheEntityInstance(DataCacheEnum cacheEnum) {
        return cacheInstance.get(cacheEnum);
    }
}
