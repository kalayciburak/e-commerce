package com.kalayciburak.inventoryservice.broker.kafka.producer;

import com.kalayciburak.commonpackage.messaging.broker.BaseProducer;
import com.kalayciburak.commonpackage.messaging.event.inventory.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductProducer {
    private final BaseProducer producer;

    public void sendProductCreatedEvent(ProductCreatedEvent event) {
        producer.sendMessage(event, "product-created");
    }
}
