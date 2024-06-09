package com.careerbuilder.careerbuilder.domain.product.db.repository;

import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;
import com.careerbuilder.careerbuilder.domain.product.db.entity.ProductFixtures;
import com.careerbuilder.careerbuilder.global.config.jpa.JpaConfig;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDto.UpdateProductDto;
import static org.assertj.core.api.Assertions.assertThat;

@Import(value = {JpaConfig.class})
@ActiveProfiles("h2-test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ProductJpaRepositoryTest {

    @Autowired
    private ProductJpaRepository sut;

    @Autowired
    TestEntityManager entityManager;

    @Nested
    class FindById {
        final Long existingId = 1L;
        final Long nonExistingId = 999L;

        @Test
        @DisplayName("id를 갖는 entity가 없다면 empty를 반환한다.")
        void test1() {
            Optional<Product> result = sut.findById(nonExistingId);
            BDDAssertions.then(result).isEmpty();
        }

        @Test
        @DisplayName("id를 갖는 entity가 있다면 entity를 반환한다.")
        void test2() {
            Optional<Product> result = sut.findById(existingId);
            BDDAssertions.then(result).isPresent();
        }
    }

    @Nested
    class FindAll {
        final int testCaseSize = 3;

        @Test
        @DisplayName("entity를 모두 반환한 수가 저장되어 있는 entity 수와 일치한다.")
        void test1() {
            List<Product> result = sut.findAll();
            BDDAssertions.then(result).hasSize(testCaseSize);
        }
    }

    @Nested
    class ExistsById {
        final Long existingId = 1L;
        final Long nonExistingId = 4L;

        @Test
        @DisplayName("id를 갖는 entity가 없다면 false를 반환한다.")
        void test1() {
            boolean result = sut.existsById(nonExistingId);
            BDDAssertions.then(result).isFalse();
        }

        @Test
        @DisplayName("id를 갖는 entity가 있다면 true를 반환한다.")
        void test2() {
            boolean result = sut.existsById(existingId);
            BDDAssertions.then(result).isTrue();
        }
    }

    @Nested
    class Delete {
        final Long existingId = 1L;
        final Long nonExistingId = 4L;

        Product existingProduct;
        Product nonExistingProduct = ProductFixtures.product(nonExistingId, "non existing Product");

        List<Product> productList;

        @BeforeEach
        void setUp() {
            existingProduct = sut.findById(existingId).get();
            productList = sut.findAll();
        }

        @Test
        @DisplayName("삭제하려는 entity가 없으면 기존 db의 변동이 없다.")
        void test1() {
            // when
            sut.delete(nonExistingProduct);
            List<Product> result = sut.findAll();

            // then
            BDDAssertions.then(result)
                    .isNotNull()
                    .hasSize(productList.size());
        }

        @Test
        @DisplayName("삭제하려는 entity가 있으면 해당 entity를 삭제한다.")
        void test2() {
            // when
            sut.delete(existingProduct);
            // then
            Product result = sut.findById(existingId).orElse(null);
            BDDAssertions.then(result).isNull();
        }
    }

    @Nested
    class Save {
        final Long existingId = 1L;
        final Long nonExistingId = 4L;

        @Test
        @DisplayName("id를 갖는 entity가 없다면 entity를 저장하고 entity를 반환한다.")
        void test1() {
            // given
            Product productEntity = Product.builder()
                    .id(nonExistingId)
                    .name("test")
                    .cost(BigDecimal.ONE)
                    .price(BigDecimal.TEN)
                    .build();

            // when
            Product result = sut.save(productEntity);

            // then
            BDDAssertions.then(result)
                    .isNotNull()
                    .isInstanceOf(Product.class)
                    .hasFieldOrPropertyWithValue("id", productEntity.getId())
                    .hasFieldOrPropertyWithValue("name", productEntity.getName());

            assertThat(result.getCreatedAt()).isNotNull();
            assertThat(result.getUpdatedAt()).isNotNull();
        }

        @Test
        @DisplayName("id를 갖는 entity가 있다면 entity를 수정하고 entity를 반환한다.")
        void test2() {
            // given
            Product entity = sut.findById(existingId).get();
            LocalDateTime createdAt = entity.getCreatedAt();
            LocalDateTime updatedAt = entity.getUpdatedAt();
            UpdateProductDto updateRequest = new UpdateProductDto(
                    "new name", null, null, BigDecimal.ZERO, BigDecimal.ZERO
            );

            // when
            entity.updateProduct(updateRequest);
            Product result = sut.save(entity);
            entityManager.flush();

            // then
            BDDAssertions.then(result)
                    .isNotNull()
                    .isInstanceOf(Product.class)
                    .hasFieldOrPropertyWithValue("id", entity.getId())
                    .hasFieldOrPropertyWithValue("name", entity.getName());

            assertThat(result.getCreatedAt()).isNotNull();
            assertThat(result.getCreatedAt()).isEqualTo(createdAt);
            assertThat(result.getUpdatedAt()).isNotNull();
            assertThat(result.getUpdatedAt()).isAfter(updatedAt);
        }
    }
}