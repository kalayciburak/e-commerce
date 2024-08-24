package com.kalayciburak.filterservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.filterservice.model.dto.response.FilterResponse;
import com.kalayciburak.filterservice.model.entity.Filter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilterMapper extends BaseMapper<FilterResponse, Filter> {
}
