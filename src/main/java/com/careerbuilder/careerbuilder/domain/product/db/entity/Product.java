package com.careerbuilder.careerbuilder.domain.product.db.entity;

import com.careerbuilder.careerbuilder.domain.product.business.dto.PartialUpdateProductRequest;
import com.careerbuilder.careerbuilder.domain.product.business.dto.UpdateProductRequest;
import com.careerbuilder.careerbuilder.global.common.baseentity.BaseEntity;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Optional;

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

    public void updateProduct(UpdateProductRequest request) {
        this.name = request.getName();
        this.barcode = request.getBarcode();
        this.photoUrl = request.getPhotoUrl();
        this.cost = request.getCost();
        this.price = request.getPrice();
    }

    public void partialUpdateProduct(PartialUpdateProductRequest request) {
        Optional.ofNullable(request.getName()).ifPresentOrElse(field->{
            this.name = field;
        }, ()-> new ApiException(ErrorCode.NULL_POINT_ERROR));
        Optional.ofNullable(request.getBarcode()).ifPresentOrElse(field->{
            this.barcode = field;
        }, ()-> new ApiException(ErrorCode.NULL_POINT_ERROR));

        Optional.ofNullable(request.getPhotoUrl()).ifPresentOrElse(field->{
            this.photoUrl = field;
        }, ()-> new ApiException(ErrorCode.NULL_POINT_ERROR));
        Optional.ofNullable(request.getCost()).ifPresentOrElse(field->{
            this.cost = field;
        }, ()-> new ApiException(ErrorCode.NULL_POINT_ERROR));
        Optional.ofNullable(request.getPrice()).ifPresentOrElse(field->{
            this.price = field;
        }, ()-> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }
}


