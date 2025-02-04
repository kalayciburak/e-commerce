package com.kalayciburak.inventoryservice.model.entitiy;

import com.kalayciburak.commonjpa.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Table(name = "reviews")
@SQLRestriction("is_active=true")
public class Review extends BaseEntity {
    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "user_id")
    private String userId;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
