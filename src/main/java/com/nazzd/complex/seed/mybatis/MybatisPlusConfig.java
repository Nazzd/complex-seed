package com.nazzd.complex.seed.mybatis;

import com.nazzd.complex.seed.load.GeneralMybatisPlusSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public GeneralMybatisPlusSqlInjector mySqlInjector() {
        return new GeneralMybatisPlusSqlInjector();
    }
}
