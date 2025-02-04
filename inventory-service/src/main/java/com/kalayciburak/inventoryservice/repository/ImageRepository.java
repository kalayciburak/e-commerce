package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonjpa.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entitiy.Image;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends BaseRepository<Image, Long> {
}
