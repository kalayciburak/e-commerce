package com.kalayciburak.inventoryservice.model.dto.request;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull(message = "Kategori ID boş olamaz")
        Long categoryId,

        @NotBlank(message = "Ürün adı boş olamaz")
        @Size(min = 2, max = 255, message = "Ürün adı en az 2 en fazla 255 karakter olabilir")
        String name,

        @Size(max = 500, message = "Açıklama en fazla 500 karakter olabilir")
        String description,

        @NotNull(message = "Fiyat boş olamaz")
        @DecimalMin(value = "0.01", message = "Fiyat en az 0.01 olmalıdır")
        BigDecimal price,

        @NotNull(message = "Stok boş olamaz")
        @Min(value = 0, message = "Stok negatif bir değer olamaz")
        Integer stock,

        @NotBlank(message = "Resim URL'i boş olamaz")
        @URL(message = "Geçerli bir URL olmalıdır")
        String imageUrl) {
}
