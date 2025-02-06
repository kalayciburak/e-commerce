package com.kalayciburak.inventoryservice.mapper;

import com.kalayciburak.commonpackage.core.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.ReviewRequest;
import com.kalayciburak.inventoryservice.model.dto.response.ReviewResponse;
import com.kalayciburak.inventoryservice.model.dto.response.product.ProductReviewResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends BaseMapper<ReviewResponse, Review> {
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "createdAt", source = "audit.createdAt")
    @Mapping(target = "updatedAt", source = "audit.updatedAt")
    ReviewResponse toResponse(Review review);

    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "audit.createdAt", source = "createdAt")
    @Mapping(target = "audit.updatedAt", source = "updatedAt")
    Review toEntity(ReviewResponse reviewResponse);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "createdAt", source = "audit.createdAt")
    @Mapping(target = "updatedAt", source = "audit.updatedAt")
    ProductReviewResponse toProductReviewResponse(Review review);

    @Mapping(target = "product.id", source = "productId")
    Review toEntity(ReviewRequest request);

    @Mapping(target = "product.id", source = "productId")
    void updateEntity(ReviewRequest request, @MappingTarget Review review);
}
