package com.kalayciburak.commonjpapackage.util.mapper;

public interface BaseMapper<RESPONSE, ENTITY> {
    RESPONSE toResponse(ENTITY entity);

    ENTITY toEntity(RESPONSE response);
}
