package com.kalayciburak.commonpackage.event.inventory;

import com.kalayciburak.commonpackage.util.event.BaseEvent;

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
