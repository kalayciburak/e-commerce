package com.kalayciburak.filterservice.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document
public class Filter {
    @Id
    private String id;
    private Long productId;
    private Long categoryId;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private String imageUrl;
    private String categoryName;
    private String categoryDescription;
}
