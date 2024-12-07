package com.kalayciburak.apigateway.loadbalancer.enums;

public enum LoadBalancerStrategy {
    RANDOM,
    ROUND_ROBIN,
    LEAST_CONNECTION;

    /**
     * Belirtilen string değerini uygun Load Balancer stratejisine dönüştürür.
     *
     * <p>Bu metot, verilen string değerine göre uygun Load Balancer stratejisini belirler.
     * Eğer belirtilen strateji bilinmiyorsa, varsayılan olarak ROUND_ROBIN stratejisi seçilir.</p>
     *
     * @param value Yük dengesini sağlamak için kullanılacak stratejinin adı
     * @return Belirtilen değere uygun Load Balancer stratejisi
     */
    public static LoadBalancerStrategy fromString(String value) {
        return switch (value.toLowerCase().replace("-", "_")) {
            case "random" -> RANDOM;
            case "least_connection" -> LEAST_CONNECTION;
            default -> ROUND_ROBIN;
        };
    }
}
