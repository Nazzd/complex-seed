package com.nazzd.complex.seed.load.enums;

import com.nazzd.complex.seed.load.entity.AbstractCacheEntity;
import com.nazzd.complex.seed.load.entity.DemoEntity;

public enum CacheLoadEnum {

    DEMO_CACHE(
            1,
            "demo-cache",
            "id",
            "select * from t_demo",
            "",
            "0 0/1 * * * ?",
            "0 0 1 * * ?",
            DemoEntity.class
    );

    private Integer dataType;

    private String cacheKey;

    private String primaryKey;

    private String sql;

    private String conditionSql;

    private String incrCornExpression;

    private String fullCornExpression;

    private Class<? extends AbstractCacheEntity> clazz;

    CacheLoadEnum(Integer dataType, String cacheKey, String primaryKey, String sql, String conditionSql, String incrCornExpression, String fullCornExpression, Class<? extends AbstractCacheEntity> clazz) {
        this.dataType = dataType;
        this.cacheKey = cacheKey;
        this.primaryKey = primaryKey;
        this.sql = sql;
        this.conditionSql = conditionSql;
        this.incrCornExpression = incrCornExpression;
        this.fullCornExpression = fullCornExpression;
        this.clazz = clazz;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getConditionSql() {
        return conditionSql;
    }

    public void setConditionSql(String conditionSql) {
        this.conditionSql = conditionSql;
    }

    public String getIncrCornExpression() {
        return incrCornExpression;
    }

    public void setIncrCornExpression(String incrCornExpression) {
        this.incrCornExpression = incrCornExpression;
    }

    public String getFullCornExpression() {
        return fullCornExpression;
    }

    public void setFullCornExpression(String fullCornExpression) {
        this.fullCornExpression = fullCornExpression;
    }

    public Class<? extends AbstractCacheEntity> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends AbstractCacheEntity> clazz) {
        this.clazz = clazz;
    }
}
