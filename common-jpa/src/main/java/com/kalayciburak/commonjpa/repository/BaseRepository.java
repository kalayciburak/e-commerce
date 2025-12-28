package com.kalayciburak.commonjpa.repository;

import com.kalayciburak.commonpackage.core.advice.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import static com.kalayciburak.commonpackage.core.constant.Messages.Entity.NOT_FOUND;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
                update #{#entityName} e
                   set e.audit.isActive = false,
                       e.audit.deletedAt = current_timestamp,
                       e.audit.deletedBy = :deletedBy
                 where e.id = :id
                   and e.audit.isActive = true
            """)
    int doSoftDeleteById(@Param("deletedBy") String deletedBy, @Param("id") ID id);

    default void softDeleteByIdOrThrow(String deletedBy, ID id) {
        int affected = doSoftDeleteById(deletedBy, id);
        if (affected == 0) throw new EntityNotFoundException(NOT_FOUND);
    }

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
                update #{#entityName} e
                   set e.audit.isActive = false,
                       e.audit.deletedAt = current_timestamp,
                       e.audit.deletedBy = :deletedBy
                 where e.id in :ids
                   and e.audit.isActive = true
            """)
    int doSoftDeleteByIds(@Param("deletedBy") String deletedBy, @Param("ids") Iterable<ID> ids);

    default void softDeleteByIdsOrThrow(String deletedBy, Iterable<ID> ids) {
        int affected = doSoftDeleteByIds(deletedBy, ids);
        if (affected == 0) throw new EntityNotFoundException(NOT_FOUND);
    }
}