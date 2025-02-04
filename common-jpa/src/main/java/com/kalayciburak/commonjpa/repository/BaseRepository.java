package com.kalayciburak.commonjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.audit.isActive = false, e.audit.deletedAt = current_timestamp, e.audit.deletedBy = ?1 where e.id = ?2")
    void softDeleteById(String deletedBy, ID id);

    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.audit.isActive = false, e.audit.deletedAt = current_timestamp, e.audit.deletedBy = ?1 where e.id in :ids")
    void softDeleteByIds(@Param("deletedBy") String deletedBy, @Param("ids") Iterable<ID> ids);
}
