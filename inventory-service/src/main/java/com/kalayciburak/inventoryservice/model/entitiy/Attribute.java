package com.kalayciburak.inventoryservice.model.entitiy;

import com.kalayciburak.commonjpapackage.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Table(name = "attributes")
@SQLRestriction("is_active=true")
public class Attribute extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
