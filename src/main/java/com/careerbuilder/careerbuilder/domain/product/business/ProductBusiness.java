package com.careerbuilder.careerbuilder.domain.product.business;

import com.careerbuilder.careerbuilder.domain.attribution.converter.AttributionConverter;
import com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto;
import com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto.ProductWithAttributionDto;
import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;
import com.careerbuilder.careerbuilder.domain.product.db.entity.ProductAttribution;
import com.careerbuilder.careerbuilder.domain.product.service.ProductAttributionService;
import com.careerbuilder.careerbuilder.domain.product.service.ProductService;
import com.careerbuilder.careerbuilder.domain.stock.entity.Stock;
import com.careerbuilder.careerbuilder.domain.stock.service.StockService;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductAttributionResponseDto.ProductAttributionResDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDto.*;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto.ProductDto;

@Business
@RequiredArgsConstructor
@Transactional
public class ProductBusiness {

    private final ProductService productService;

    private final ProductAttributionService productAttributionService;

    private final StockService stockService;

    private final AttributionConverter attributionConverter;

    public ProductDto register(CreateProductDto request) {
        // 제품 등록
        Product product = request.toDomain();
        Product registered = productService.register(product);
        // 초기 재고 등록
        stockService.saveStock(
                Stock.builder()
                        .productId(registered.getId())
                        .locationId(request.locationId())
                        .stockQuantity(request.initialQuantity())
                        .build()
        );
        return ProductResponseDto.ProductDto.fromDomain(registered);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getProductList() {
        List<Product> productList = productService.getProductList();

        return productList.stream()
                .map(ProductResponseDto.ProductDto::fromDomain)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDto getProductById(Long productId) {
        Product product = productService.getProductById(productId);
        return ProductResponseDto.ProductDto.fromDomain(product);
    }

    @Transactional(readOnly = true)
    public ProductWithAttributionDto getProductWithAttributionsById(
            Long productId
    ) {
        // 제품 조회
        Product product = productService.getProductById(productId);

        // 제품-속성 리스트 조회
        List<ProductAttribution> productAttributions = productAttributionService
                .getProductAttributionByProductId(productId);

        List<ProductAttributionResDto> list = productAttributions.stream()
                .map(it -> {
                    return new ProductAttributionResDto(
                            attributionConverter.toResponse(it.getAttribution()),
                            it.getAttributionValue()
                    );
                }).toList();

        return new ProductWithAttributionDto(
                ProductResponseDto.ProductDto.fromDomain(product), list
        );
    }

    public ProductDto updateProductById(Long productId, UpdateProductDto request) {
        // 제품 조회 & 수정
        Product product = productService.getProductById(productId);
        product.updateProduct(request);

        return ProductResponseDto.ProductDto.fromDomain(product);
    }

    public void deleteProductById(Long productId) {
        // 제품 삭제
        productService.deleteProductById(productId);
    }

    public List<ProductDto> searchProductByKeyword(SearchProductDto request) {
        // 제품 검색
        List<Product> productList = productService.searchProduct(
                request.keyword(), request.field()
        );

        return productList.stream()
                .map(ProductResponseDto.ProductDto::fromDomain)
                .collect(Collectors.toList());
    }
}
