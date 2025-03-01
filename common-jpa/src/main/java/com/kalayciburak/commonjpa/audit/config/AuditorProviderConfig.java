package com.kalayciburak.commonjpa.audit.config;

import com.kalayciburak.commonjpa.audit.AuditorProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditorProviderConfig {
    @Bean
    @ConditionalOnMissingBean(AuditorProvider.class)
    public AuditorProvider defaultAuditorProvider() {
        return () -> "system";
    }
}