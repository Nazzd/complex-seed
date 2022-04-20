package com.nazzd.complex.seed.task;

import com.nazzd.complex.seed.task.unit.UnitConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@Data
@ConfigurationProperties(prefix = "unit")
public class UnitFactory {

    private Map<String, UnitConfig> base;


}
