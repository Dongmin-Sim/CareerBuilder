package com.careerbuilder.careerbuilder.domain.product.business.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterProductRequest {

    @NotBlank
    @Size(min = 2, max = 255)
    private String name;

    private String barcode;

    private String photoUrl;

    @NotNull
    private BigDecimal cost;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Long locationId;

    @NotNull
    private Integer initialQuantity;
}
