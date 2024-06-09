package com.careerbuilder.careerbuilder.domain.product.controller;

import com.careerbuilder.careerbuilder.domain.product.business.ProductBusiness;
import com.careerbuilder.careerbuilder.domain.product.business.dto.*;
import com.careerbuilder.careerbuilder.global.common.api.Api;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductBusiness productBusiness;

    @PostMapping
    public Api<ProductDto> register(
            @RequestBody @Valid
            ProductRequestDto.CreateProductDto request
    ) {
        var response = productBusiness.register(request);
        return Api.OK(response);
    }

    @GetMapping("/{productId}")
    public Api<ProductWithAttributionDto> getProductById(
            @Positive @PathVariable Long productId
    ) {
        var response = productBusiness.getProductWithAttributionsById(productId);
        return Api.OK(response);
    }

    @GetMapping
    public Api<List<ProductDto>> getProductList() {
        var response = productBusiness.getProductList();
        return Api.OK(response);
    }

    @PutMapping("/{productId}")
    public Api<ProductDto> updateProduct(
            @Positive @PathVariable Long productId,
            @RequestBody @Valid ProductRequestDto.UpdateProductDto request
    ) {
        var response = productBusiness.updateProductById(productId, request);
        return Api.OK(response);
    }

    @DeleteMapping("/{productId}")
    public Api<ProductDto> deleteProduct(
            @Positive @PathVariable Long productId
    ) {
        productBusiness.deleteProductById(productId);
        return Api.OK(null);
    }
}
