package com.nazzd.complex.seed.cache;

import com.nazzd.complex.seed.modules.auth.enums.CacheName;

public interface Cache {

    <T> T get(CacheName cacheName, String key, Class<T> clazz);

    void put(CacheName cacheName, String key, Object value);

    void remove(CacheName cacheName, String key);
}
