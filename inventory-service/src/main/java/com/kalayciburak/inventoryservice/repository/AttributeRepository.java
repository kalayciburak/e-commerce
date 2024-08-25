package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonjpapackage.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entitiy.Attribute;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeRepository extends BaseRepository<Attribute, Long> {
    Optional<Attribute> findByName(String name);

    boolean existsByNameAndProductId(String name, Long productId);
}
