package com.careerbuilder.careerbuilder.domain.product.entity;

import com.careerbuilder.careerbuilder.global.common.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 200)
    private String barcode;

    @Column(length = 200)
    private String photoUrl;

    @Column(precision = 12, scale = 4)
    private BigDecimal cost;

    @Column(precision = 12, scale = 4)
    private BigDecimal price;
}


