package com.nazzd.complex.seed.load;

import com.nazzd.complex.seed.load.cache.AbstractLoadSource;
import com.nazzd.complex.seed.service.LoadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class LoadCacheFactory {

    @Resource
    private List<AbstractLoadSource> cacheList;

    @Resource
    private LoadService loadService;

    /**
     * 记录每一类缓存当前同步的时间戳
     */
    public static final Map<String, LocalDateTime> CURRENT_SYNC_TIMESTAMP = new ConcurrentHashMap<>();

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
        cacheList.forEach(AbstractLoadSource::init);
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
