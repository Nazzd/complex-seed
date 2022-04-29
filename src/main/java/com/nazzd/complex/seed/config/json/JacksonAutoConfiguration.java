package com.nazzd.complex.seed.config.json;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.nazzd.complex.seed.config.json.deserializer.TrimStringDeserializer;
import com.nazzd.complex.seed.config.json.serializer.LongToStringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JacksonProperties.class)
public class JacksonAutoConfiguration {

    @Bean
    @ConditionalOnProperty(value = "nazzd.seed.deserialization.trim-string", matchIfMissing = true)
    public TrimStringDeserializer trimStringDeserializer() {
        return new TrimStringDeserializer();
    }

    @Configuration
    @ConditionalOnProperty(value = "nazzd.seed.jackson.serialization.big-number-to-string", matchIfMissing = true)
    public static class BigNumberToStringConfiguration {

        @Bean
        public Module bigLongToStringModule() {
            SimpleModule module = new SimpleModule();
            module.addSerializer(Long.class, LongToStringSerializer.INSTANCE);
            module.addSerializer(Long.TYPE, LongToStringSerializer.INSTANCE);
            return module;
        }

    }


}
