package com.kalayciburak.filterservice.repository;

import com.kalayciburak.filterservice.model.entity.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilterRepository extends MongoRepository<Filter, String> {
}
