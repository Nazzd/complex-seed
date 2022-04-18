package com.nazzd.complex.seed.load.entity;

import java.time.LocalDateTime;

public abstract class AbstractCacheEntity {

    public abstract String geyCacheKey();

    public abstract String geyQuerySql();

    public abstract String geyIncrSql(LocalDateTime lastIncrTime);
}
