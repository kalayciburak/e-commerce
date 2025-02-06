package com.kalayciburak.inventoryservice.messaging.producer;

import com.kalayciburak.commonpackage.messaging.event.inventory.product.ProductCreatedEvent;
import com.kalayciburak.commonpackage.messaging.producer.BaseProducer;
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
