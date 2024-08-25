package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonjpapackage.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entitiy.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends BaseRepository<Review, Long> {
}
