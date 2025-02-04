package com.kalayciburak.inventoryservice.model.entitiy;

import com.kalayciburak.commonjpa.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Table(name = "images")
@SQLRestriction("is_active=true")
public class Image extends BaseEntity {
    @Column(name = "url")
    private String url;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
