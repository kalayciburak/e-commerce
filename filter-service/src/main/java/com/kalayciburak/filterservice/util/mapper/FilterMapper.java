package com.kalayciburak.filterservice.util.mapper;

import com.kalayciburak.commonpackage.event.inventory.ProductCreatedEvent;
import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.filterservice.model.dto.response.FilterResponse;
import com.kalayciburak.filterservice.model.entity.Filter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FilterMapper extends BaseMapper<FilterResponse, Filter> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productId", source = "id")
    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "category.name", source = "categoryName")
    @Mapping(target = "category.description", source = "categoryDescription")
    Filter toEntity(ProductCreatedEvent event);
}
