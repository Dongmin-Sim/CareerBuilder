package com.careerbuilder.careerbuilder.domain.product.controller;

import com.careerbuilder.careerbuilder.domain.product.business.ProductBusiness;
import com.careerbuilder.careerbuilder.domain.product.dto.*;
import com.careerbuilder.careerbuilder.global.common.api.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductBusiness productBusiness;

    @PostMapping
    public Api<ProductResponse> register(
            @RequestBody @Valid
            RegisterProductRequest request
    ) {
        var response = productBusiness.register(request);
        return Api.OK(response);
    }

    @GetMapping
    public Api<List<ProductResponse>> getProductList() {
        var response = productBusiness.getProductList();
        return Api.OK(response);
    }

    @GetMapping("/{productId}")
    public Api<ProductWithAttributionsResponse> getProductById(
            @PathVariable Long productId
    ) {
        var response = productBusiness.getProductWithAttributionsById(productId);
        return Api.OK(response);
    }

    @PutMapping("/{productId}")
    public Api<ProductWithAttributionsResponse> updateProduct(
            @PathVariable Long productId,
            @RequestBody @Valid UpdateProductRequest request
    ) {
        var response = productBusiness.updateProductById(productId, request);
        return Api.OK(response);
    }

    @PatchMapping("/{productId}")
    public Api<ProductWithAttributionsResponse> partialUpdateProduct(
            @PathVariable Long productId,
            @RequestBody @Valid PartialUpdateProductRequest request
    ) {
        var response = productBusiness.partialUpdateProductById(productId, request);
        return Api.OK(response);
    }

    @DeleteMapping("/{productId}")
    public Api<ProductResponse> deleteProduct(
            @PathVariable Long productId
    ) {
        productBusiness.deleteProductById(productId);
        return Api.OK(null);
    }
}
