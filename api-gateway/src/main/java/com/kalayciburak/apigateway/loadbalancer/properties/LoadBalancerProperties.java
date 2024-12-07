package com.kalayciburak.apigateway.loadbalancer.properties;

import com.kalayciburak.apigateway.loadbalancer.enums.LoadBalancerStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "loadbalancer")
public class LoadBalancerProperties {
    private LoadBalancerStrategy strategy;

    /**
     * Load Balancer stratejisini {@link LoadBalancerStrategy#fromString(String)} metoduyla döndürür.
     *
     * @return Load Balancer stratejisi
     */
    public LoadBalancerStrategy getStrategy() {
        return LoadBalancerStrategy.fromString(String.valueOf(strategy));
    }

    public void setStrategy(LoadBalancerStrategy strategy) {
        this.strategy = strategy;
    }
}
