package com.kalayciburak.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class GatewayConfig {
    /**
     * Özel bir route yapılandırması sağlar.
     *
     * <p>Bu metot, API Gateway için özel bir rota oluşturur. Örneğin, "/inventory-service/api/**" ile eşleşen
     * istekler, yük dengeleme (load balancing) kullanılarak "inventory-service" servisine yönlendirilir. Ayrıca, isteklerin
     * "Cookie" başlığı kaldırılır, URI'deki ilk segment kaldırılır (stripPrefix), yeniden deneme (retry) ve devre kesici
     * (circuit breaker) özellikleri eklenir. Devre kesici aktifleştiğinde fallback URI'yi kullanır.
     *
     * @param builder {@link RouteLocatorBuilder} türünde, route oluşturmak için kullanılan yapılandırma nesnesi
     * @return {@link RouteLocator} ile yapılandırılmış rotaları döner.
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("inventory-service", r -> r
                        .path("/inventory-service/api/**")
                        .and()
                        .method("GET", "POST", "PUT", "DELETE")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .stripPrefix(1)
                                .retry(config -> config
                                        .setRetries(1)
                                        .setStatuses(HttpStatus.SERVICE_UNAVAILABLE))
                                .circuitBreaker(config -> config
                                        .setName("inventoryService")
                                        .setFallbackUri("forward:/fallback")
                                        .setRouteId("inventory-service")))
                        .uri("lb://inventory-service"))
                .build();
    }
}
