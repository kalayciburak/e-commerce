package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonjpapackage.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entitiy.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
}
