package com.careerbuilder.careerbuilder.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {

    @NotBlank
    private String name;

    @NotNull
    private String barcode;

    @NotNull
    private String photoUrl;

    @NotNull
    private BigDecimal cost;

    @NotNull
    private BigDecimal price;
}
