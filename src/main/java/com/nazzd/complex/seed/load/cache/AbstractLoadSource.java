package com.nazzd.complex.seed.load.cache;

import com.nazzd.complex.seed.load.enums.CacheLoadEnum;
import com.nazzd.complex.seed.load.source.ISource;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractLoadSource implements SchedulingConfigurer {

    protected ISource iSource;

    public abstract CacheLoadEnum getLoadEnum();

    protected AbstractLoadSource(ISource iSource) {
        this.iSource = iSource;
    }

    /**
     * 初始化,加载全量缓存至内存中
     */
    public void init() {
        this.triggerFullCache();

    }

    /**
     * 触发全量缓存更新
     */
    public void triggerFullCache() {
        // 更新缓存数据,全量
        storeCache(iSource.fullLoad(this.getLoadEnum()), false);

    }

    /**
     * 触发增量缓存更新
     */
    public void triggerIncrCache() {
        // 更新缓存数据,增量
        storeCache(iSource.incrLoad(this.getLoadEnum()), false);
    }

    /**
     * 将数据存入实际的业务缓存中
     *
     * @param dataList 全量数据
     * @param isIncr   是否是增量
     */
    public abstract void storeCache(List<Map<String, Object>> dataList, boolean isIncr);

    /**
     * 配置定时任务(corn表达式为空时不进行配置)
     *
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        CacheLoadEnum loadEnum = this.getLoadEnum();
        if (Strings.isNotEmpty(loadEnum.getFullCornExpression())) {
            taskRegistrar.addCronTask(this::triggerFullCache, CacheLoadEnum.DEMO_CACHE.getFullCornExpression());
        }
        if (Strings.isNotEmpty(loadEnum.getIncrCornExpression())) {
            taskRegistrar.addCronTask(this::triggerIncrCache, CacheLoadEnum.DEMO_CACHE.getIncrCornExpression());
        }

    }
}
