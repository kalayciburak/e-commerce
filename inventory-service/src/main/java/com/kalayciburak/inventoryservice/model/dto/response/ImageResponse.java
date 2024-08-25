package com.kalayciburak.inventoryservice.model.dto.response;

public record ImageResponse(
        Long id,
        Long productId,
        String url
) {
}
