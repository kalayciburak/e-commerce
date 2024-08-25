package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonjpapackage.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entitiy.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
    Optional<Product> findByName(String name);

    @EntityGraph(attributePaths = {"reviews"})
    List<Product> findAll();
}
