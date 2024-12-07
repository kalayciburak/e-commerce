package com.kalayciburak.apigateway.loadbalancer.config;

import com.kalayciburak.apigateway.loadbalancer.factory.LoadBalancerFactory;
import com.kalayciburak.apigateway.loadbalancer.properties.LoadBalancerProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@LoadBalancerClients(defaultConfiguration = LoadBalancerConfig.class)
public class LoadBalancerConfig {

    private final LoadBalancerFactory loadBalancerFactory;
    private final LoadBalancerProperties properties;

    public LoadBalancerConfig(LoadBalancerProperties properties, LoadBalancerFactory loadBalancerFactory) {
        this.properties = properties;
        this.loadBalancerFactory = loadBalancerFactory;
    }

    /**
     * Bu metot, belirtilen yük dengesini (Load Balancer) stratejisine göre bir ReactorLoadBalancer yaratır ve Spring Cloud
     * Gateway'e yükler.
     *
     * <p>Bu örnekte, load balancer stratejisi çevresel değişkenlerden alınır ve stratejiye uygun bir ReactorLoadBalancer
     * yaratılır. Yaratılan Load Balancer, belirli bir istek stratejisine uygun olarak çalışır.</p>
     *
     * @param env     Çevresel değişkenleri sağlayan Environment nesnesi
     * @param factory LoadBalancerClientFactory nesnesi, Load Balancer üretimi için kullanılır
     * @return Belirtilen stratejiye uygun bir şekilde yaratılmış ReactorLoadBalancer nesnesi
     */
    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment env, LoadBalancerClientFactory factory) {
        var serviceId = env.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);

        return loadBalancerFactory.createLoadBalancer(properties.getStrategy(), factory, serviceId);
    }
}
