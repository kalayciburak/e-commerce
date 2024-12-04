package com.kalayciburak.filterservice.model.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record FilterResponse(
        String id,
        Long productId,
        String name,
        String description,
        BigDecimal price,
        int stock,
        CategoryInfo category,
        List<ImageInfo> images,
        List<AttributeInfo> attributes,
        List<ReviewInfo> reviews) {
    public record CategoryInfo(
            Long id,
            String name,
            String description) {}

    public record ImageInfo(
            Long id,
            String url) {}

    public record AttributeInfo(
            Long id,
            String name,
            String value) {}

    public record ReviewInfo(
            Long id,
            String userId,
            Long rating,
            String comment) {}
}

