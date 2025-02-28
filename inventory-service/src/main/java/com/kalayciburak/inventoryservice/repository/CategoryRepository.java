package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonjpa.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entitiy.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
    Optional<Category> findByName(String name);

    boolean existsByName(String name);

    List<Category> findAllByParentIsNull();
}
