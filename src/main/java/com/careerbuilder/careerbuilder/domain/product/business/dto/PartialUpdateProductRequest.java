package com.careerbuilder.careerbuilder.domain.product.business.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartialUpdateProductRequest {

    private String name;

    private String barcode;

    private String photoUrl;

    private BigDecimal cost;

    private BigDecimal price;
}
