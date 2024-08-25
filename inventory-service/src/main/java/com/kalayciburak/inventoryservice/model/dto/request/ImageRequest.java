package com.kalayciburak.inventoryservice.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record ImageRequest(
        @NotNull(message = "Ürün ID boş olamaz")
        Long productId,

        @NotBlank(message = "Resim URL'i boş olamaz")
        @URL(message = "Geçerli bir URL olmalıdır")
        String url
) {
}
