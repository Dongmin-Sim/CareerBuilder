package com.careerbuilder.careerbuilder.domain.product.business;

import com.careerbuilder.careerbuilder.domain.attribution.converter.AttributionConverter;
import com.careerbuilder.careerbuilder.domain.attribution.db.entity.AttributionFixtures;
import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;
import com.careerbuilder.careerbuilder.domain.product.db.entity.ProductAttribution;
import com.careerbuilder.careerbuilder.domain.product.db.entity.ProductAttributionFixtures;
import com.careerbuilder.careerbuilder.domain.product.service.ProductAttributionService;
import com.careerbuilder.careerbuilder.domain.product.service.ProductService;
import com.careerbuilder.careerbuilder.domain.product.service.error.ProductErrorCode;
import com.careerbuilder.careerbuilder.domain.stock.service.StockService;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDto.*;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDtoFixtures.createProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDtoFixtures.updateProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto.ProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto.ProductWithAttributionDto;
import static com.careerbuilder.careerbuilder.domain.product.db.entity.ProductFixtures.product;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ProductBusinessTest {

    @InjectMocks
    ProductBusiness sut;

    @Mock
    ProductService productService;
    @Mock
    StockService stockService;
    @Mock
    ProductAttributionService productAttributionService;
    @Spy
    AttributionConverter attributionConverter;

    @DisplayName("제품 등록")
    @Nested
    class RegisterProduct {
        @Test
        @DisplayName("제품 정보와 위치 그리고 초기수량 입력하면 재고를 등록하고 제품을 등록한다.")
        void test1() {
            // given
            Product product = product();
            CreateProductDto createRequest = createProductDto();

            given(productService.register(any()))
                    .willReturn(product);
            given(stockService.saveStock(any()))
                    .willReturn(null);

            // when
            var result = sut.register(createRequest);

            // then
            BDDAssertions.then(result)
                    .isNotNull()
                    .isInstanceOf(ProductDto.class)
                    .hasFieldOrPropertyWithValue("name", product.getName())
                    .hasFieldOrPropertyWithValue("barcode", product.getBarcode())
                    .hasFieldOrPropertyWithValue("photoUrl", product.getPhotoUrl());

            then(productService).should().register(any());
            then(stockService).should().saveStock(any());
        }
    }

    @DisplayName("제품 조회, 단건")
    @Nested
    class FindByProductId {
        final Long productId = 1L;

        @Test
        @DisplayName("productId를 갖는 제품을 찾지 못하면 exception(PRODUCT_NOT_FOUND)을 throw한다.")
        void test1() {
            // given
            given(productService.getProductById(productId))
                    .willThrow(new ApiException(ProductErrorCode.PRODUCT_NOT_FOUND));

            // when & then
            BDDAssertions.thenThrownBy(() -> sut.getProductById(productId))
                    .isInstanceOf(ApiException.class)
                    .hasFieldOrPropertyWithValue("errorCodeIfs", ProductErrorCode.PRODUCT_NOT_FOUND)
                    .hasFieldOrPropertyWithValue("errorMessage", ProductErrorCode.PRODUCT_NOT_FOUND.getErrorMessage());

            then(productService).should().getProductById(productId);
        }

        @Test
        @DisplayName("productId를 갖는 제품을 반환한다.")
        void test1000() {
            // given
            Product product = product(productId);
            given(productService.getProductById(eq(productId)))
                    .willReturn(product);

            // when
            var result = sut.getProductById(productId);

            // then
            BDDAssertions.then(result)
                    .isNotNull()
                    .isInstanceOf(ProductDto.class)
                    .hasFieldOrPropertyWithValue("name", product.getName())
                    .hasFieldOrPropertyWithValue("cost", product.getCost())
                    .hasFieldOrPropertyWithValue("price", product.getPrice());

            then(productService).should().getProductById(eq(productId));
        }

        @Test
        @DisplayName("productId를 갖는 제품과 제품이 가진 속성들을 함께 반환한다.")
        void test1001() {
            // given
            Product product = product(productId);

            Attribution attribution = AttributionFixtures.attribution();

            ProductAttribution productAttribution1 = ProductAttributionFixtures.productAttribution(1L, product, attribution);
            ProductAttribution productAttribution2 = ProductAttributionFixtures.productAttribution(2L, product, attribution);

            // stubbing
            given(productService.getProductById(eq(productId))).willReturn(product);
            given(productAttributionService.getProductAttributionByProductId(eq(productId)))
                    .willReturn(List.of(productAttribution1, productAttribution2));

            // when
            var result = sut.getProductWithAttributionsById(productId);

            // then
            BDDAssertions.then(result)
                    .isNotNull()
                    .isInstanceOf(ProductWithAttributionDto.class)
                    .hasFieldOrPropertyWithValue("product.id", product.getId())
                    .hasFieldOrPropertyWithValue("product.name", product.getName());
            BDDAssertions.then(result.productAttributions().get(0))
                    .hasFieldOrPropertyWithValue("attribution.id", attribution.getId())
                    .hasFieldOrPropertyWithValue("value", productAttribution1.getAttributionValue());
            BDDAssertions.then(result.productAttributions().get(1))
                    .hasFieldOrPropertyWithValue("attribution.id", attribution.getId())
                    .hasFieldOrPropertyWithValue("value", productAttribution2.getAttributionValue());
        }
    }

    @DisplayName("제품 조회, 리스트")
    @Nested
    class FindProducts {
        @DisplayName("제품 목록 리스트를 조회한다.")
        @Test
        void test1() {
            // given
            List<Product> productList = List.of(
                    product(1L, "apple"),
                    product(2L,  "samsung")
            );

            given(productService.getProductList())
                    .willReturn(productList);

            // when
            var result = sut.getProductList();

            // then
            BDDAssertions.then(result)
                    .hasSize(2);
            BDDAssertions.then(result.get(0))
                    .hasFieldOrPropertyWithValue("id", productList.get(0).getId())
                    .hasFieldOrPropertyWithValue("name", productList.get(0).getName());
            BDDAssertions.then(result.get(1))
                    .hasFieldOrPropertyWithValue("id", productList.get(1).getId())
                    .hasFieldOrPropertyWithValue("name", productList.get(1).getName());

            then(productService).should().getProductList();
        }

        @Test
        @DisplayName("제품 목록 리스트가 없는 경우 빈 리스트를 반환한다.")
        void test2() {
            given(productService.getProductList())
                    .willReturn(List.of());

            // when
            var result = sut.getProductList();

            // then
            BDDAssertions.then(result).isEmpty();

            then(productService).should().getProductList();
        }
    }

    @DisplayName("제품 수정")
    @Nested
    class UpdateProduct {
        final Long productId = 1L;

        @DisplayName("productId를 갖는 제품을 찾지 못하면 API exception(PRODUCT_NOT_FOUND)을 throw한다.")
        @Test
        void test1() {
            // given
            UpdateProductDto updateRequest = updateProductDto();
            given(productService.getProductById(eq(productId)))
                    .willThrow(new ApiException(ProductErrorCode.PRODUCT_NOT_FOUND));

            // when && then
            BDDAssertions.thenThrownBy(() -> sut.updateProductById(productId, updateRequest))
                    .isInstanceOf(ApiException.class)
                    .hasFieldOrPropertyWithValue("errorCodeIfs", ProductErrorCode.PRODUCT_NOT_FOUND)
                    .hasFieldOrPropertyWithValue("errorMessage", ProductErrorCode.PRODUCT_NOT_FOUND.getErrorMessage());

            then(productService).should().getProductById(eq(productId));
        }

        @DisplayName("productId를 갖는 제품을 찾으면 제품을 수정하고 반환한다.")
        @Test
        void test100() {
            // given
            Product product = product(productId);
            given(productService.getProductById(eq(productId)))
                    .willReturn(product);

            // when
            UpdateProductDto updateRequest = updateProductDto(
                    "newName",
                    "newBarcode",
                    "newPhoto"
            );
            var result = sut.updateProductById(productId, updateRequest);

            // then
            BDDAssertions.then(result)
                    .isNotNull()
                    .isInstanceOf(ProductDto.class)
                    .hasFieldOrPropertyWithValue("id", product.getId())
                    .hasFieldOrPropertyWithValue("name", updateRequest.name())
                    .hasFieldOrPropertyWithValue("barcode", updateRequest.barcode())
                    .hasFieldOrPropertyWithValue("photoUrl", updateRequest.photoUrl());

            then(productService).should().getProductById(eq(productId));
        }
    }

    @DisplayName("제품 삭제")
    @Nested
    class DeleteProduct {
        final Long productId = 1L;

        @DisplayName("productId를 갖는 제품을 찾지 못하면 API exception(PRODUCT_NOT_FOUND)을 throw한다.")
        @Test
        void test1() {
            // given
            willThrow(new ApiException(ProductErrorCode.PRODUCT_NOT_FOUND))
                    .given(productService).deleteProductById(productId);

            // when
            BDDAssertions.thenThrownBy(() -> sut.deleteProductById(productId))
                    .isInstanceOf(ApiException.class)
                    .hasFieldOrPropertyWithValue("errorCodeIfs", ProductErrorCode.PRODUCT_NOT_FOUND)
                    .hasFieldOrPropertyWithValue("errorMessage", ProductErrorCode.PRODUCT_NOT_FOUND.getErrorMessage());

            // then
            then(productService).should().deleteProductById(eq(productId));
        }

        @DisplayName("productId가 주어지면, productId를 갖는 제품을 삭제하고 리턴하지 않는다.")
        @Test
        void test1000() {
            // given
            willDoNothing().given(productService).deleteProductById(eq(productId));

            // when
            sut.deleteProductById(productId);

            // then
            then(productService).should().deleteProductById(eq(productId));
        }
    }

    @DisplayName("제품 검색")
    @Nested
    class SearchProductByKeyword {
        final String keyword = "keyword";
        final String field = "field";

        SearchProductDto request;

        @BeforeEach
        void setUp() {
            request = new SearchProductDto(keyword, field);
        }

        @Test
        @DisplayName("키워드에 맞지 않는 제품이 존재하지 않을 경우 빈 리스트를 리턴한다.")
        void test1() {
            // given
            BDDMockito.given(productService.searchProduct(any(), any()))
                    .willReturn(List.of());

            // when
            var result = sut.searchProductByKeyword(request);

            // then
            BDDAssertions.then(result)
                    .isEmpty();
        }

        @Test
        @DisplayName("검색 필드가 유효하지 않은 경우, Exception을 throw 한다.")
        void test2() {
            // given
            BDDMockito.given(productService.searchProduct(any(), any()))
                    .willThrow(new ApiException(ProductErrorCode.INVALID_SEARCH_FIELD));

            // when
            BDDAssertions.assertThatThrownBy(() -> sut.searchProductByKeyword(request))
                    .isInstanceOf(ApiException.class)
                    .hasFieldOrPropertyWithValue("errorCodeIfs", ProductErrorCode.INVALID_SEARCH_FIELD)
                    .hasFieldOrPropertyWithValue("errorMessage", ProductErrorCode.INVALID_SEARCH_FIELD.getErrorMessage());
        }

        @Test
        @DisplayName("키워드에 맞는 제품이 존재할 경우 제품 리스트를 리턴한다.")
        void test1000() {
            // given
            Product product1 = product(1L);
            Product product2 = product(2L);
            BDDMockito.given(productService.searchProduct(any(), any()))
                    .willReturn(List.of(
                            product1, product2
                    ));

            // when
            List<ProductDto> result = sut.searchProductByKeyword(request);

            // then
            BDDAssertions.then(result).hasSize(2);
            BDDAssertions.then(result.get(0))
                    .hasFieldOrPropertyWithValue("id", product1.getId());
            BDDAssertions.then(result.get(1))
                    .hasFieldOrPropertyWithValue("id", product2.getId());
        }
    }
}