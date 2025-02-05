package com.kalayciburak.commonjpa.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class Audit {
    protected static final String TIMEZONE = "UTC";
    protected static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

    @Column(name = "is_active")
    private boolean isActive = true;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonFormat(pattern = DATE_TIME_FORMAT, timezone = TIMEZONE)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    @JsonFormat(pattern = DATE_TIME_FORMAT, timezone = TIMEZONE)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    @JsonFormat(pattern = DATE_TIME_FORMAT, timezone = TIMEZONE)
    private LocalDateTime deletedAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;

    @Column(name = "deleted_by")
    private String deletedBy;
}
