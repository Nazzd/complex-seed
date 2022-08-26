package com.nazzd.complex.seed.load;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.StopWatch;
import com.google.common.collect.Lists;
import com.nazzd.complex.seed.load.entity.AbstractCacheEntity;
import com.nazzd.complex.seed.load.enums.DataCacheEnum;
import com.nazzd.complex.seed.modules.auth.service.LoadService;
import com.nazzd.complex.seed.utils.SeedSpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

//@Component
@Slf4j
public class LoadCacheFactory {

    @Resource
    private LoadService loadService;

    /**
     * 记录每一类缓存当前同步的时间戳
     */
    public static final Map<DataCacheEnum, LocalDateTime> CURRENT_SYNC_TIMESTAMP = new ConcurrentHashMap<>();

    /**
     * 最大尝试次数
     */
    @Value("${kite.service.cache.maxTryCount:3}")
    private int maxTryCount;

    /**
     * 数据库是否可用
     */
    public static volatile boolean IS_USABLE = true;

    public static void setIsUsable(boolean isUsable) {
        IS_USABLE = isUsable;
    }

    /**
     * 系统启动加载的时候必须先加载全部缓存到本地,否则可能拿不到数据
     */
    @PostConstruct
    public void init() {
        checkDatabaseHealth();
        // 缓存初始化
        scheduleFullLoad();
    }

    @Scheduled(cron = "${cache.full.fixRate:0 0 4 * * ?}")
    private void scheduleFullLoad() {
        for (DataCacheEnum ele : DataCacheEnum.values()) {
            loadFullCache(ele);
        }
    }

    @Scheduled(cron = "${cache.incr.fixRate:0 */5 * * * ?}")
    private void scheduleIncrLoad() {
        for (DataCacheEnum ele : DataCacheEnum.values()) {
            loadIncrCache(ele);
        }
    }

    private void loadFullCache(DataCacheEnum ele) {
        log.info("load full cache :{}", ele);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        if (IS_USABLE) {
            try {
                AbstractCacheEntity cacheEntityInstance = DataCacheEnum.getAbstractCacheEntityInstance(ele);
                // 从数据源获取数据
                List<Map<String, Object>> maps = loadService.queryMaps(cacheEntityInstance.geyQuerySql());

                List<? extends AbstractCacheEntity> abstractCacheEntities = convertMaps2ListBean(ele.getCacheEntityClass(), maps);

                SeedSpringContextUtil.getBean(ele.getCacheManager()).processFullCache(abstractCacheEntities);
                log.error("{}全量加载缓存成功,size:{}", ele, abstractCacheEntities.size());
            } catch (Exception e) {
                log.error("{}全量加载缓存失败", ele, e);
            }
            CURRENT_SYNC_TIMESTAMP.put(ele, LocalDateTime.now());
        } else {
            log.error("数据库异常,本次不加载数据");
        }
        stopWatch.stop();
        log.info("loadFullCache:{},本次耗时:{}", ele, stopWatch.getTotalTimeMillis());

    }

    private void loadIncrCache(DataCacheEnum ele) {
        log.info("load incr cache :{}", ele);
        AbstractCacheEntity cacheEntityInstance = DataCacheEnum.getAbstractCacheEntityInstance(ele);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        LocalDateTime lastIncrTime = CURRENT_SYNC_TIMESTAMP.get(ele);
        if (Objects.isNull(lastIncrTime)) {
            lastIncrTime = LocalDateTime.now();
        }
        String incrSql = cacheEntityInstance.geyIncrSql(lastIncrTime);
        if (Objects.isNull(incrSql)) {
            log.info("{}当前缓存不支持增量缓存", ele);
            return;
        }
        if (IS_USABLE) {
            try {
                // 从数据源获取数据
                List<Map<String, Object>> maps = loadService.queryMaps(incrSql);

                List<? extends AbstractCacheEntity> abstractCacheEntities = convertMaps2ListBean(ele.getCacheEntityClass(), maps);

                SeedSpringContextUtil.getBean(ele.getCacheManager()).processIncrCache(abstractCacheEntities);
                log.error("{}增量加载缓存成功,size:{}", ele, abstractCacheEntities.size());
            } catch (Exception e) {
                log.error("{}增量加载缓存失败", ele, e);
            }
            CURRENT_SYNC_TIMESTAMP.put(ele, LocalDateTime.now());
        } else {
            log.error("数据库异常,本次不加载数据");
        }
        stopWatch.stop();
        log.info("loadFullCache:{},本次耗时:{}", ele, stopWatch.getTotalTimeMillis());

    }


    public <T> List<T> convertMaps2ListBean(Class<T> clazz, List<Map<String, Object>> maps) {
        List<T> result = Lists.newArrayList();
        if (CollectionUtil.isNotEmpty(maps)) {
            for (Map<String, Object> map : maps) {
                T element = null;
                try {
                    element = clazz.newInstance();
                    BeanUtil.fillBeanWithMap(map, element, true, true);
                } catch (Exception e) {
                    log.error("Map 转换出错 clazz:{},map:{}", clazz.getName(), map);
                }
                if (null != element) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    /**
     * 定时检测数据库是否可用
     */
    @Scheduled(cron = "${schedule.check-database-health:0 */2 * * * ?}")
    private void checkDatabaseHealth() {
        log.info("-----------------------------检测数据库是否可用-----------------------------");
        for (int i = 1; i <= maxTryCount; i++) {
            boolean isSuccess = true;
            try {
                loadService.healthCheck();
            } catch (Exception e) {
                log.error(i + "次数据库检测连接失败,错误原因:{}", e.getMessage(), e);
                isSuccess = false;

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
            if (isSuccess) {
                LoadCacheFactory.setIsUsable(isSuccess);
                log.info("-----------------------------数据库连接正常-----------------------------");
                break;
            } else {
                // 失败的情况
                if (i == maxTryCount) {
                    LoadCacheFactory.setIsUsable(false);
                    log.info("数据库第{}次检测异常,设置成失败状态", i);
                } else {
                    log.info("数据库第{}次检测异常", i);

                }
            }
        }
    }

}
