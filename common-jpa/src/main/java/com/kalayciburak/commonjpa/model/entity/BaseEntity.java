package com.kalayciburak.commonjpa.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.InvalidClassException;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
    /**
     * <b>Serileştirilebilir bir sınıf için evrensel bir sürüm tanımlayıcısıdır.</b>
     * <p>
     * Deserialization işlemi sırasında bu numara, yüklenen sınıfın tam olarak serileştirilmiş bir nesneye karşılık geldiğini
     * doğrulamak için kullanılır. Eğer eşleşme bulunamazsa, {@link InvalidClassException} hatası fırlatılır.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Audit audit = new Audit();
}
