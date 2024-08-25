package com.kalayciburak.inventoryservice.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AttributeRequest(
        @NotNull(message = "Ürün ID boş olamaz")
        Long productId,

        @NotBlank(message = "Özellik adı boş olamaz")
        @Size(min = 2, max = 255, message = "Özellik adı en az 2 en fazla 255 karakter olabilir")
        String name,

        @NotBlank(message = "Özellik değeri boş olamaz")
        @Size(min = 2, max = 255, message = "Özellik değeri en az 2 en fazla 255 karakter olabilir")
        String value
) {
}
