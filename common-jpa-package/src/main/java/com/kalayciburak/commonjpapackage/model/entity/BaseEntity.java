package com.kalayciburak.commonjpapackage.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.InvalidClassException;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
    protected static final String TIMEZONE = "UTC";
    protected static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

    /**
     * <b>Serileştirilebilir bir sınıf için evrensel bir sürüm tanımlayıcısıdır.</b>
     * <p>
     * Deserialization işlemi sırasında bu numara, yüklenen sınıfın tam olarak serileştirilmiş
     * bir nesneye karşılık geldiğini doğrulamak için kullanılır. Eğer eşleşme bulunamazsa,
     * {@link InvalidClassException} hatası fırlatılır.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    @JsonFormat(pattern = DATE_TIME_FORMAT, timezone = TIMEZONE)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated", insertable = false)
    @JsonFormat(pattern = DATE_TIME_FORMAT, timezone = TIMEZONE)
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    @JsonFormat(pattern = DATE_TIME_FORMAT, timezone = TIMEZONE)
    private LocalDateTime deletedAt;

    @Column(name = "is_active")
    private boolean isActive = true;
}
