package com.kalayciburak.inventoryservice.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "Kategori adı boş olamaz")
        @Size(min = 2, max = 100, message = "Kategori adı en az 2 en fazla 100 karakter olabilir")
        String name,

        @Size(max = 500, message = "Açıklama en fazla 500 karakter olabilir")
        String description) {
}
