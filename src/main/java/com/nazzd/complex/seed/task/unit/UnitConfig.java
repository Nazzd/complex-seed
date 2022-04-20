package com.nazzd.complex.seed.task.unit;

import lombok.Data;

/**
 * 任务单元的配置信息
 */
@Data
public class UnitConfig {

    private String id;

    private int poolSize;

    private int queueSize;

    private Long timeOuts;
}
