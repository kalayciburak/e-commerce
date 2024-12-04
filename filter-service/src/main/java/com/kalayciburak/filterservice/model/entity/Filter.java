package com.kalayciburak.filterservice.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Document
public class Filter {
    @Id
    private String id;
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private CategoryInfo category;
    private List<ImageInfo> images;
    private List<AttributeInfo> attributes;
    private List<ReviewInfo> reviews;

    @Getter
    @Setter
    public static class CategoryInfo {
        private Long id;
        private String name;
        private String description;
    }

    @Getter
    @Setter
    public static class ImageInfo {
        private Long id;
        private String url;
    }

    @Getter
    @Setter
    public static class AttributeInfo {
        private Long id;
        private String name;
        private String value;
    }

    @Getter
    @Setter
    public static class ReviewInfo {
        private Long id;
        private String userId;
        private int rating;
        private String comment;
    }
}
