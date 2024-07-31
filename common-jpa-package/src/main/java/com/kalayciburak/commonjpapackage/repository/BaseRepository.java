package com.kalayciburak.commonjpapackage.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.isActive = false, e.deletedAt = current_timestamp where e.id = ?1")
    void softDeleteById(ID id);

    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.isActive = false, e.deletedAt = current_timestamp where e.id in :ids")
    void softDeleteByIds(@Param("ids") Iterable<ID> ids);
}
