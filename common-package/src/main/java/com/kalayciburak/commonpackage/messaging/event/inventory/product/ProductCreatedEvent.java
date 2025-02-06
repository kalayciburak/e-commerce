package com.kalayciburak.commonpackage.messaging.event.inventory.product;

import com.kalayciburak.commonpackage.messaging.event.BaseEvent;

import java.math.BigDecimal;

public record ProductCreatedEvent(
        // Ürün bilgileri
        String id,
        String name,
        String description,
        BigDecimal price,
        int stock,

        // Kategori bilgileri
        String categoryId,
        String categoryName,
        String categoryDescription
) implements BaseEvent {
}
