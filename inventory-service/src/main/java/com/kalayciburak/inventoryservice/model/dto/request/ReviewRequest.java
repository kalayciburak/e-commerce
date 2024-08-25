package com.kalayciburak.inventoryservice.model.dto.request;

import jakarta.validation.constraints.*;

public record ReviewRequest(
        @NotNull(message = "Kategori ID boş olamaz")
        Long productId,

        @NotBlank(message = "Kullanıcı ID boş olamaz")
        String userId,

        @NotNull(message = "Puan boş olamaz")
        @Min(value = 1, message = "Puan en az 1 olabilir")
        @Max(value = 5, message = "Puan en fazla 5 olabilir")
        Integer rating,

        @NotBlank(message = "Yorum boş olamaz")
        @Size(min = 2, max = 500, message = "Yorum en az 2 en fazla 500 karakter olabilir")
        String comment
) {
}
