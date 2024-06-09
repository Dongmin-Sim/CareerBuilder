package com.careerbuilder.careerbuilder.domain.product.controller;

import com.careerbuilder.careerbuilder.domain.attribution.dto.AttributionResponseDto;
import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.attribution.entity.type.AttributionType;
import com.careerbuilder.careerbuilder.domain.product.business.ProductBusiness;
import com.careerbuilder.careerbuilder.domain.product.business.dto.ProductAttributionResponseDto;
import com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDto;
import com.careerbuilder.careerbuilder.domain.product.service.error.ProductErrorCode;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import com.careerbuilder.careerbuilder.global.config.objectmapper.ObjectMapperConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.careerbuilder.careerbuilder.domain.attribution.dto.AttributionResponseDto.*;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDto.CreateProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDtoFixtures.createProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDtoFixtures.updateProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto.ProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto.ProductWithAttributionDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDtoFixtures.productDto;
import static com.careerbuilder.careerbuilder.test.assertions.CustomAssertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(value = {ObjectMapperConfig.class})
@WebMvcTest(controllers = ProductApiController.class)
public class ProductApiControllerTest {

    @MockBean
    ProductBusiness productBusiness;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);

    @Nested
    @DisplayName("제품 등록 [POST]: /api/v1/products")
    class RegisterProduct {
        final String path = "/api/v1/products";

        @Test
        @DisplayName("요청 값이 유효하지 않은 경우 400 status와 error 리턴")
        void test1() throws Exception {
            CreateProductDto productDto = createProductDto(
                    null, null, null, null, null, 1L, 10
            );

            final String body = objectMapper.writeValueAsString(productDto);
            final MvcResult result = mockMvc.perform(post(path)
                            .contentType(contentType)
                            .content(body)
                    )
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn();

            assertMvcErrorEquals(result, ErrorCode.VALIDATION_ERROR);
        }

        @Test
        @DisplayName("정상 요청의 경우 200 status와 제품 정보 리턴")
        void test1000() throws Exception {
            final CreateProductDto productDto = createProductDto();
            ProductDto responseProduct = productDto();
            given(productBusiness.register(any()))
                    .willReturn(responseProduct);

            final String requestBody = objectMapper.writeValueAsString(productDto);
            MvcResult result = mockMvc.perform(
                            post(path)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody)
                    )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            assertMvcDataEquals(result, (dataField) -> {
                assertProductEqual(dataField, responseProduct);
            });

        }

    }

    @Nested
    @DisplayName("제품 단건 조회 [GET]: /api/v1/products/{productId}")
    class FindProductById {

        final String path = "/api/v1/products/{productId}";
        final Long productId = 1L;

        @Test
        @DisplayName("요청 파라미터가 음수일 경우, 400 status와 error 리턴")
        void test1() throws Exception {
            MvcResult result = mockMvc.perform(get(path, -1L))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn();

            assertMvcErrorEquals(result, ErrorCode.VALIDATION_ERROR);
        }

        @Test
        @DisplayName("제품이 존재하지 않는 경우 404 status와 error 리턴")
        void test2() throws Exception {
            given(productBusiness.getProductWithAttributionsById(productId))
                    .willThrow(new ApiException(ProductErrorCode.PRODUCT_NOT_FOUND));

            MvcResult result = mockMvc.perform(get(path, productId))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn();

            assertMvcErrorEquals(result, ProductErrorCode.PRODUCT_NOT_FOUND);
        }

        @Test
        @DisplayName("정상 요청의 경우 200 status와 제품 정보 리턴")
        void test1000() throws Exception {
            ProductDto productDto = productDto(productId);
            Attribution attribution = Attribution.builder()
                    .id(1L)
                    .attributionType(AttributionType.STRING)
                    .attributionName("maker")
                    .rankNum(1)
                    .build();
            ProductWithAttributionDto responseDto = new ProductWithAttributionDto(
                    productDto,
                    List.of(
                            new ProductAttributionResponseDto.AttributionValueResponseDto(
                                    AttributionResponse.fromDomain(attribution), "apple"
                            ),
                            new ProductAttributionResponseDto.AttributionValueResponseDto(
                                    AttributionResponse.fromDomain(attribution), "samsung"
                            )
                    )
            );
            given(productBusiness.getProductWithAttributionsById(productId))
                    .willReturn(responseDto);

            MvcResult result = mockMvc.perform(get(path, productId))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            assertMvcDataEquals(result, (dataField) -> {
                JsonNode productField = dataField.get("product");
                assertThat(productField).isNotNull();
                assertProductEqual(productField, productDto);

                JsonNode productAttributionsField = dataField.get("productAttributions");
                assertThat(productAttributionsField).isNotNull();
                assertThat(productAttributionsField).hasSize(2);

                ArrayNode productAttributions = (ArrayNode) productAttributionsField;

                assertAttributionEqual(productAttributions.get(0), attribution);
                assertThat(productAttributions.get(0).get("value").asText()).isEqualTo("apple");

                assertAttributionEqual(productAttributions.get(1), attribution);
                assertThat(productAttributions.get(1).get("value").asText()).isEqualTo("samsung");
            });
        }

        private static void assertAttributionEqual(JsonNode productAttribution, Attribution attributionResponse) {
            assertThat(productAttribution.get("attribution")).isNotNull();
            assertThat(productAttribution.get("attribution").get("id").asLong()).isEqualTo(attributionResponse.getId());
            assertThat(productAttribution.get("attribution").get("type").asText()).isEqualTo(attributionResponse.getAttributionType().toString());
            assertThat(productAttribution.get("attribution").get("name").asText()).isEqualTo(attributionResponse.getAttributionName());
            assertThat(productAttribution.get("attribution").get("rankNum").asInt()).isEqualTo(attributionResponse.getRankNum());
        }

    }

    @Nested
    @DisplayName("제품 리스트 조회 [GET]: /api/v1/products")
    class FindProducts {

        final String path = "/api/v1/products";

        @Test
        @DisplayName("정상의 경우 200 status와 제품 정보 리턴")
        void test1000() throws Exception {
            ProductDto productDto1 = productDto(1L);
            ProductDto productDto2 = productDto(2L);
            given(productBusiness.getProductList())
                    .willReturn(List.of(
                            productDto1,
                            productDto2
                    ));

            MvcResult result = mockMvc.perform(get(path))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            assertMvcDataEquals(result, (dataField) -> {
                ArrayNode productList = (ArrayNode) dataField;
                assertThat(productList).isNotNull();
                assertThat(productList).hasSize(2);

                assertProductEqual(productList.get(0), productDto1);
                assertProductEqual(productList.get(1), productDto2);
            });
        }

        @Test
        @DisplayName("제품이 존재하지 않는 경우 200 status와 빈 리스트 리턴")
        void test1001() throws Exception {
            MvcResult result = mockMvc.perform(get(path))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            assertMvcDataEquals(result, (dataField) -> {
                ArrayNode productList = (ArrayNode) dataField;
                assertThat(productList).hasSize(0);
            });
        }

    }

    @Nested
    @DisplayName("제품 수정 [PUT]: /api/v1/products/{productId}")
    class UpdateProduct {

        final String path = "/api/v1/products/{productId}";
        final Long negativeProductId = -1L;
        final Long productId = 1L;
        final ProductRequestDto.UpdateProductDto updateRequest = updateProductDto();

        @Test
        @DisplayName("요청 파라미터가 음수일 경우, 400 status와 error 리턴")
        void test1() throws Exception {
            String requestBody = objectMapper.writeValueAsString(updateRequest);
            MvcResult result = mockMvc.perform(put(path, negativeProductId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody)
                    )
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn();

            assertMvcErrorEquals(result, ErrorCode.VALIDATION_ERROR);
        }

        @Test
        @DisplayName("제품이 존재하지 않는 경우 404 status와 error 리턴")
        void test2() throws Exception {
            given(productBusiness.updateProductById(productId, updateRequest))
                    .willThrow(new ApiException(ProductErrorCode.PRODUCT_NOT_FOUND));

            String requestBody = objectMapper.writeValueAsString(updateRequest);
            MvcResult result = mockMvc.perform(put(path, productId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody)
                    )
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn();

            assertMvcErrorEquals(result, ProductErrorCode.PRODUCT_NOT_FOUND);
        }

        @Test
        @DisplayName("정상의 경우 200 status와 제품 정보 리턴")
        void test1000() throws Exception {
            ProductDto productDto = productDto(1L);
            given(productBusiness.updateProductById(productId, updateRequest))
                    .willReturn(productDto);

            String requestBody = objectMapper.writeValueAsString(updateRequest);
            MvcResult result = mockMvc.perform(put(path, productId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody)
                    )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            assertMvcDataEquals(result, (dataField) -> {
                assertProductEqual(dataField, productDto);
            });
        }
    }

    @Nested
    @DisplayName("제품 삭제 [DELETE]: /api/v1/products/{productId}")
    class DeleteProduct {
        final String path = "/api/v1/products/{productId}";
        final Long productId = 1L;

        @Test
        @DisplayName("제품이 존재하지 않는 경우 404 status와 error 리턴")
        void test1() throws Exception {
            BDDMockito.willThrow(new ApiException(ProductErrorCode.PRODUCT_NOT_FOUND))
                    .given(productBusiness).deleteProductById(productId);

            // when
            MvcResult result = mockMvc.perform(delete(path, productId))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn();

            // then
            assertMvcErrorEquals(result, ProductErrorCode.PRODUCT_NOT_FOUND);
        }

        @Test
        @DisplayName("정상의 경우 200 status와 api 호출 결과 리턴")
        void test1000() throws Exception {
            // given
            BDDMockito.willDoNothing()
                    .given(productBusiness).deleteProductById(productId);
            // when
            MvcResult result = mockMvc.perform(delete(path, productId))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            // then
            assertMvcSuccessEquals(result, true);
        }
    }

    private static void assertProductEqual(JsonNode dataField, ProductDto responseProduct) {
        assertThat(dataField.get("id").asLong()).isEqualTo(responseProduct.id());
        assertThat(dataField.get("name").asText()).isEqualTo(responseProduct.name());
        assertThat(dataField.get("barcode").asText()).isEqualTo(responseProduct.barcode());
        assertThat(dataField.get("photoUrl").asText()).isEqualTo(responseProduct.photoUrl());
        assertThat(dataField.get("cost").asInt()).isEqualTo(responseProduct.cost().intValue());
        assertThat(dataField.get("price").asInt()).isEqualTo(responseProduct.price().intValue());
    }
}
