package com.careerbuilder.careerbuilder.domain.product.db.entity;

import com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDto;
import com.careerbuilder.careerbuilder.global.common.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
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

    public void updateProduct(ProductRequestDto.UpdateProductDto request) {
        this.name = request.name();
        this.barcode = request.barcode();
        this.photoUrl = request.photoUrl();
        this.cost = request.cost();
        this.price = request.price();
    }
}


