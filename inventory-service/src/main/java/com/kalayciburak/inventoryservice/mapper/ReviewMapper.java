package com.kalayciburak.inventoryservice.mapper;

import com.kalayciburak.commonpackage.core.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.ReviewRequest;
import com.kalayciburak.inventoryservice.model.dto.response.review.ReviewResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends BaseMapper<ReviewResponse, Review> {
    @Mapping(target = "productId", source = "product.id")
    ReviewResponse toResponse(Review review);

    @Mapping(target = "product.id", source = "productId")
    Review toEntity(ReviewResponse reviewResponse);

    @Mapping(target = "product.id", source = "productId")
    Review toEntity(ReviewRequest request);

    @Mapping(target = "product.id", source = "productId")
    void updateEntity(ReviewRequest request, @MappingTarget Review review);
}
