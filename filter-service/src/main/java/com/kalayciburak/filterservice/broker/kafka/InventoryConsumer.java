package com.kalayciburak.filterservice.broker.kafka;

import com.kalayciburak.commonpackage.event.inventory.ProductCreatedEvent;
import com.kalayciburak.commonpackage.util.event.BaseEvent;
import com.kalayciburak.filterservice.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryConsumer {
    private static final Logger log = LoggerFactory.getLogger(InventoryConsumer.class);
    private final FilterService service;

    /**
     * <b>"product-created" konusunu dinler ve gelen mesajları işler.</b>
     * <p>
     * Bu metot, Kafka'dan gelen {@link ProductCreatedEvent} mesajlarını dinler ve işleme alır. Mesaj alındığında
     * {@link #logConsumedEvent(BaseEvent)} metodu ile loglama yapılır ve ardından
     * {@link FilterService#onProductCreatedEvent(ProductCreatedEvent)} metodu çağrılarak event işlenir.
     *
     * @param event Dinlenen {@link ProductCreatedEvent} türünde event nesnesi
     */
    @KafkaListener(topics = "product-created")
    public void consume(ProductCreatedEvent event) {
        logConsumedEvent(event);
        service.onProductCreatedEvent(event);
    }

    /**
     * <b>Tüketilen event'i loglar.</b>
     * <p>
     * Bu metot, Kafka'dan tüketilen bir event'in bilgilerini loglamak için kullanılır.
     *
     * @param event Loglanacak {@link BaseEvent} türünde event nesnesi
     */
    private void logConsumedEvent(BaseEvent event) {
        log.info("Consumed event: {}", event);
    }
}
