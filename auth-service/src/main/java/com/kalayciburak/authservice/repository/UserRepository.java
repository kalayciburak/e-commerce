package com.kalayciburak.authservice.repository;

import com.kalayciburak.authservice.model.entity.User;
import com.kalayciburak.commonjpa.repository.BaseRepository;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from User u where u.id = :id and u.audit.isActive = true")
    Optional<User> findActiveByIdForUpdate(@Param("id") Long id);
}
