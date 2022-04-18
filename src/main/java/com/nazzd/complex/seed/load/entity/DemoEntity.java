package com.nazzd.complex.seed.load.entity;

import java.time.LocalDateTime;

public class DemoEntity extends AbstractCacheEntity {

    private Long id;

    @Override
    public String geyCacheKey() {
        return String.valueOf(id);
    }

    @Override
    public String geyQuerySql() {
        return "select * from t_demo";
    }

    @Override
    public String geyIncrSql(LocalDateTime lastIncrTime) {
        return null;
    }


}
