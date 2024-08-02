package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonjpapackage.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entitiy.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
}
