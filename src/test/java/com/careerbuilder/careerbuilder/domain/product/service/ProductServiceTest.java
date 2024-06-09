package com.careerbuilder.careerbuilder.domain.product.service;

import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;
import com.careerbuilder.careerbuilder.domain.product.db.entity.ProductFixtures;
import com.careerbuilder.careerbuilder.domain.product.db.repository.ProductJpaRepository;
import com.careerbuilder.careerbuilder.domain.product.service.error.ProductErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService sut;

    @Mock
    ProductJpaRepository productJpaRepository;

    @Nested
    class Register {
        @Test
        @DisplayName("entity가 주어지면 entity를 저장하고 저장한 entity를 리턴한다.")
        void test1() {
            // given
            Product entity = ProductFixtures.product();
            given(productJpaRepository.save(entity))
                    .willReturn(entity);
            // when
            var result = sut.register(entity);

            // then
            BDDAssertions.then(result)
                    .isNotNull()
                    .isInstanceOf(Product.class)
                    .hasFieldOrPropertyWithValue("id", entity.getId())
                    .hasFieldOrPropertyWithValue("name", entity.getName())
                    .hasFieldOrPropertyWithValue("barcode", entity.getBarcode())
                    .hasFieldOrPropertyWithValue("cost", entity.getCost())
                    .hasFieldOrPropertyWithValue("price", entity.getPrice());
        }
    }

    @Nested
    class GetProduct {
        final Long existingId = 1L;

        @Test
        @DisplayName("id를 갖는 entity를 찾지 못하면 ApiException을 throw 한다.")
        void test1() {
            // given
            given(productJpaRepository.findById(any()))
                    .willReturn(Optional.empty());
            // when & then
            assertThatThrownBy(() -> sut.getProductById(existingId))
                    .isInstanceOf(ApiException.class)
                    .hasFieldOrPropertyWithValue("errorCodeIfs", ProductErrorCode.PRODUCT_NOT_FOUND)
                    .hasFieldOrPropertyWithValue("errorMessage", ProductErrorCode.PRODUCT_NOT_FOUND.getErrorMessage());
            then(productJpaRepository).should().findById(any());
        }

        @Test
        @DisplayName("id를 갖는 entity를 찾으면 entity를 반환한다.")
        void test1000() {
            // given
            Product entity = ProductFixtures.product();
            given(productJpaRepository.findById(existingId))
                    .willReturn(Optional.of(entity));
            // when
            var result = sut.getProductById(existingId);
            BDDAssertions.then(result)
                    .isNotNull()
                    .isInstanceOf(Product.class)
                    .hasFieldOrPropertyWithValue("id", entity.getId())
                    .hasFieldOrPropertyWithValue("name", entity.getName())
                    .hasFieldOrPropertyWithValue("barcode", entity.getBarcode())
                    .hasFieldOrPropertyWithValue("cost", entity.getCost())
                    .hasFieldOrPropertyWithValue("price", entity.getPrice());
        }

        @Test
        @DisplayName("모든 entity 리스트를 반환한다.")
        void test1001() {
            given(productJpaRepository.findAll())
                    .willReturn(List.of(ProductFixtures.product(), ProductFixtures.product()));
            // when
            var result = sut.getProductList();

            BDDAssertions.then(result)
                    .isNotEmpty()
                    .hasSize(2);
            then(productJpaRepository).should().findAll();
        }

        @Test
        @DisplayName("entity가 없을 경우 빈 리스트를 반환한다.")
        void test1002() {
            given(productJpaRepository.findAll())
                    .willReturn(List.of());
            // when
            var result = sut.getProductList();

            // then
            BDDAssertions.then(result).isEmpty();
            then(productJpaRepository).should().findAll();
        }


    }

    @Nested
    class ExistProduct {
        @Test
        @DisplayName("id를 갖는 entity를 찾지 못하면 false를 리턴한다.")
        void test1() {
            given(productJpaRepository.existsById(any()))
                    .willReturn(false);
            var result = sut.isExist(-1L);
            BDDAssertions.then(result).isFalse();
        }

        @Test
        @DisplayName("id를 갖는 entity를 찾으면 true를 리턴한다.")
        void test1000() {
            given(productJpaRepository.existsById(any()))
                    .willReturn(true);
            var result = sut.isExist(1L);
            BDDAssertions.then(result).isTrue();
        }
    }

    @Nested
    class DeleteProduct {
        final Long existingId = 1L;
        final Long nonExistingId = -1L;

        @Test
        @DisplayName("id를 갖는 entity를 찾지 못하면 delete 호출않고 ApiException을 throw 한다.")
        void test1() {
            // given
            given(productJpaRepository.findById(any()))
                    .willReturn(Optional.empty());

            // when
            assertThatThrownBy(() -> sut.deleteProductById(nonExistingId))
                    .isNotNull()
                    .isInstanceOf(ApiException.class)
                    .hasFieldOrPropertyWithValue("errorCodeIfs", ProductErrorCode.PRODUCT_NOT_FOUND)
                    .hasFieldOrPropertyWithValue("errorMessage", ProductErrorCode.PRODUCT_NOT_FOUND.getErrorMessage());

            then(productJpaRepository).should(times(0)).delete(any());
        }

        @Test
        @DisplayName("id를 갖는 entity를 찾으면 아무것도 반환하지 않는다.")
        void test1000() {
            // given
            Product entity = ProductFixtures.product();
            given(productJpaRepository.findById(any()))
                    .willReturn(Optional.of(entity));
            willDoNothing().given(productJpaRepository).delete(any());

            // when
            sut.deleteProductById(existingId);

            // then
            then(productJpaRepository).should().findById(any());
            then(productJpaRepository).should().delete(any());
        }
    }
}