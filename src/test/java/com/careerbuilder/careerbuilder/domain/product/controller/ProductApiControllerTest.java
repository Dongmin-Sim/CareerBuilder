package com.careerbuilder.careerbuilder.domain.product.controller;

import com.careerbuilder.careerbuilder.domain.attribution.dto.AttributionResponse;
import com.careerbuilder.careerbuilder.domain.attribution.entity.type.AttributionType;
import com.careerbuilder.careerbuilder.domain.product.business.ProductBusiness;
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

import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductAttributionResponseDto.ProductAttributionResDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDto.CreateProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDto.UpdateProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDtoFixtures.createProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductRequestDtoFixtures.updateProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto.ProductDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto.ProductWithAttributionDto;
import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDtoFixtures.productDto;
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

    @Nested
    @DisplayName("제품 등록 [POST]: /api/products/")
    class RegisterProduct {
        @Test
        @DisplayName("요청 값이 유효하지 않은 경우 400 status와 error 리턴")
        void test1() throws Exception {
            // given
            CreateProductDto productDto = createProductDto(
                    null, null, null, null, null, 1L, 10
            );

            // when
            final String body = objectMapper.writeValueAsString(productDto);
            final MvcResult result = mockMvc.perform(post("/api/v1/products")
                            .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                            .content(body)
                    )
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(successField.asBoolean()).isEqualTo(false);
            assertThat(errorField).isNotNull();
            assertThat(dataField).isNull();
            assertThat(errorField.get("code").asText()).isEqualTo(ErrorCode.VALIDATION_ERROR.getErrorCode());
            assertThat(errorField.get("message").asText()).isEqualTo(ErrorCode.VALIDATION_ERROR.getErrorMessage());
        }

        @Test
        @DisplayName("정상 요청의 경우 200 status와 제품 정보 리턴")
        void test1000() throws Exception {
            // given
            final CreateProductDto productDto = createProductDto();
            ProductDto responseProduct = productDto();
            given(productBusiness.register(any()))
                    .willReturn(responseProduct);

            // when
            final String requestBody = objectMapper.writeValueAsString(productDto);
            MvcResult result = mockMvc.perform(
                            post("/api/v1/products")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody)
                    )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(errorField).isNull();
            assertThat(dataField).isNotNull();

            assertThat(successField.asBoolean()).isEqualTo(true);
            assertThat(dataField.get("id").asLong()).isEqualTo(responseProduct.id());
            assertThat(dataField.get("name").asText()).isEqualTo(responseProduct.name());
            assertThat(dataField.get("barcode").asText()).isEqualTo(responseProduct.barcode());
            assertThat(dataField.get("photoUrl").asText()).isEqualTo(responseProduct.photoUrl());
            assertThat(dataField.get("cost").asInt()).isEqualTo(responseProduct.cost().intValue());
            assertThat(dataField.get("price").asInt()).isEqualTo(responseProduct.price().intValue());
        }
    }

    @Nested
    @DisplayName("제품 단건 조회 [GET]: /api/v1/products/{productId}")
    class FindProductById {
        @Test
        @DisplayName("요청 파라미터가 음수일 경우, 400 status와 error 리턴")
        void test1() throws Exception {
            // given
            Long productId = -1L;
            // when
            MvcResult result = mockMvc.perform(
                            get("/api/v1/products/{productId}", productId)
                    )
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(errorField).isNotNull();
            assertThat(dataField).isNull();

            assertThat(successField.asBoolean()).isEqualTo(false);
            assertThat(errorField.get("code").asText()).isEqualTo(ErrorCode.VALIDATION_ERROR.getErrorCode());
            assertThat(errorField.get("message").asText()).isEqualTo(ErrorCode.VALIDATION_ERROR.getErrorMessage());
        }

        @Test
        @DisplayName("제품이 존재하지 않는 경우 404 status와 error 리턴")
        void test2() throws Exception {
            // given
            Long productId = 1L;
            given(productBusiness.getProductWithAttributionsById(productId))
                    .willThrow(new ApiException(ProductErrorCode.PRODUCT_NOT_FOUND));

            // when
            MvcResult result = mockMvc.perform(
                            get("/api/v1/products/{productId}", productId)
                    )
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(errorField).isNotNull();
            assertThat(dataField).isNull();

            assertThat(successField.asBoolean()).isEqualTo(false);
            assertThat(errorField.get("code").asText()).isEqualTo(ProductErrorCode.PRODUCT_NOT_FOUND.getErrorCode());
            assertThat(errorField.get("message").asText()).isEqualTo(ProductErrorCode.PRODUCT_NOT_FOUND.getErrorMessage());
        }

        @Test
        @DisplayName("정상 요청의 경우 200 status와 제품 정보 리턴")
        void test1000() throws Exception {
            // given
            Long productId = 1L;
            ProductDto productDto = productDto(1L);
            AttributionResponse attributionResponse = AttributionResponse.builder()
                    .id(1L)
                    .attributionType(AttributionType.STRING)
                    .attributionName("maker")
                    .rankNum(1)
                    .build();

            ProductWithAttributionDto responseDto = new ProductWithAttributionDto(
                    productDto,
                    List.of(
                            new ProductAttributionResDto(attributionResponse, "apple"),
                            new ProductAttributionResDto(attributionResponse, "samsung")
                    )
            );
            given(productBusiness.getProductWithAttributionsById(productId))
                    .willReturn(responseDto);
            // when
            MvcResult result = mockMvc.perform(get("/api/v1/products/{productId}", productId))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(successField.asBoolean()).isEqualTo(true);
            assertThat(errorField).isNull();
            assertThat(dataField).isNotNull();

            // product check
            JsonNode productField = dataField.get("product");
            assertThat(productField).isNotNull();
            assertThat(productField.get("id").asLong()).isEqualTo(productDto.id());
            assertThat(productField.get("name").asText()).isEqualTo(productDto.name());
            assertThat(productField.get("barcode").asText()).isEqualTo(productDto.barcode());
            assertThat(productField.get("photoUrl").asText()).isEqualTo(productDto.photoUrl());
            assertThat(productField.get("cost").asInt()).isEqualTo(productDto.cost().intValue());
            assertThat(productField.get("price").asInt()).isEqualTo(productDto.price().intValue());

            // productAttributions check
            JsonNode productAttributionsField = dataField.get("productAttributions");
            assertThat(productAttributionsField).isNotNull();
            assertThat(productAttributionsField).hasSize(2);
            ArrayNode productAttributions = (ArrayNode) productAttributionsField;
            assertThat(productAttributions.get(0).get("attribution")).isNotNull();
            assertThat(productAttributions.get(0).get("attribution").get("id").asLong()).isEqualTo(attributionResponse.getId());
            assertThat(productAttributions.get(0).get("attribution").get("type").asText()).isEqualTo(attributionResponse.getAttributionType().toString());
            assertThat(productAttributions.get(0).get("attribution").get("name").asText()).isEqualTo(attributionResponse.getAttributionName());
            assertThat(productAttributions.get(0).get("attribution").get("rankNum").asInt()).isEqualTo(attributionResponse.getRankNum());
            assertThat(productAttributions.get(0).get("value").asText()).isEqualTo("apple");
            assertThat(productAttributions.get(1).get("attribution")).isNotNull();
            assertThat(productAttributions.get(1).get("attribution").get("id").asLong()).isEqualTo(attributionResponse.getId());
            assertThat(productAttributions.get(1).get("attribution").get("type").asText()).isEqualTo(attributionResponse.getAttributionType().toString());
            assertThat(productAttributions.get(1).get("attribution").get("name").asText()).isEqualTo(attributionResponse.getAttributionName());
            assertThat(productAttributions.get(1).get("attribution").get("rankNum").asInt()).isEqualTo(attributionResponse.getRankNum());
            assertThat(productAttributions.get(1).get("value").asText()).isEqualTo("samsung");
        }
    }

    @Nested
    @DisplayName("제품 리스트 조회 [GET]: /api/v1/products")
    class FindProducts {
        @Test
        @DisplayName("정상의 경우 200 status와 제품 정보 리턴")
        void test1000() throws Exception {
            // given
            ProductDto productDto1 = productDto(1L);
            ProductDto productDto2 = productDto(2L);
            given(productBusiness.getProductList())
                    .willReturn(List.of(
                            productDto1,
                            productDto2
                    ));

            // when
            MvcResult result = mockMvc.perform(get("/api/v1/products"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(successField.asBoolean()).isEqualTo(true);
            assertThat(errorField).isNull();
            assertThat(dataField).isNotNull();

            ArrayNode productList = (ArrayNode) dataField;
            assertThat(productList).isNotNull();
            assertThat(productList).hasSize(2);
            assertThat(productList.get(0).get("id").asLong()).isEqualTo(productDto1.id());
            assertThat(productList.get(0).get("name").asText()).isEqualTo(productDto1.name());
            assertThat(productList.get(0).get("barcode").asText()).isEqualTo(productDto1.barcode());
            assertThat(productList.get(0).get("photoUrl").asText()).isEqualTo(productDto1.photoUrl());
            assertThat(productList.get(0).get("cost").asInt()).isEqualTo(productDto1.cost().intValue());
            assertThat(productList.get(0).get("price").asInt()).isEqualTo(productDto1.price().intValue());

            assertThat(productList.get(1).get("id").asLong()).isEqualTo(productDto2.id());
            assertThat(productList.get(1).get("name").asText()).isEqualTo(productDto2.name());
            assertThat(productList.get(1).get("barcode").asText()).isEqualTo(productDto2.barcode());
            assertThat(productList.get(1).get("photoUrl").asText()).isEqualTo(productDto2.photoUrl());
            assertThat(productList.get(1).get("cost").asInt()).isEqualTo(productDto2.cost().intValue());
            assertThat(productList.get(1).get("price").asInt()).isEqualTo(productDto2.price().intValue());
        }

        @Test
        @DisplayName("제품이 존재하지 않는 경우 200 status와 빈 리스트 리턴")
        void test1001() throws Exception {
            // given

            // when
            MvcResult result = mockMvc.perform(get("/api/v1/products"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(successField.asBoolean()).isEqualTo(true);
            assertThat(errorField).isNull();
            assertThat(dataField).isNotNull();

            ArrayNode productList = (ArrayNode) dataField;
            assertThat(productList).hasSize(0);
        }
    }

    @Nested
    @DisplayName("제품 수정 [PUT]: /api/v1/products/{productId}")
    class UpdateProduct {

        @Test
        @DisplayName("요청 파라미터가 음수일 경우, 400 status와 error 리턴")
        void test1() throws Exception {
            // given
            Long productId = -1L;

            // when
            String requestBody = objectMapper.writeValueAsString(updateProductDto());
            MvcResult result = mockMvc.perform(put("/api/v1/products/{productId}", productId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody)
                    )
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(errorField).isNotNull();
            assertThat(dataField).isNull();

            assertThat(successField.asBoolean()).isEqualTo(false);
            assertThat(errorField.get("code").asText()).isEqualTo(ErrorCode.VALIDATION_ERROR.getErrorCode());
            assertThat(errorField.get("message").asText()).isEqualTo(ErrorCode.VALIDATION_ERROR.getErrorMessage());
        }

        @Test
        @DisplayName("제품이 존재하지 않는 경우 404 status와 error 리턴")
        void test2() throws Exception {
            // when
            Long productId = 1L;
            UpdateProductDto request = updateProductDto();
            given(productBusiness.updateProductById(productId, request))
                    .willThrow(new ApiException(ProductErrorCode.PRODUCT_NOT_FOUND));

            // then
            String requestBody = objectMapper.writeValueAsString(updateProductDto());
            MvcResult result = mockMvc.perform(put("/api/v1/products/{productId}", productId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody)
                    )
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(errorField).isNotNull();
            assertThat(dataField).isNull();

            assertThat(successField.asBoolean()).isEqualTo(false);
            assertThat(errorField.get("code").asText()).isEqualTo(ProductErrorCode.PRODUCT_NOT_FOUND.getErrorCode());
            assertThat(errorField.get("message").asText()).isEqualTo(ProductErrorCode.PRODUCT_NOT_FOUND.getErrorMessage());
        }

        @Test
        @DisplayName("정상의 경우 200 status와 제품 정보 리턴")
        void test1000() throws Exception {
            // given
            Long productId = 1L;
            UpdateProductDto request = updateProductDto();
            ProductDto productDto = productDto();
            given(productBusiness.updateProductById(productId, request))
                    .willReturn(productDto);

            // when
            String requestBody = objectMapper.writeValueAsString(request);
            MvcResult result = mockMvc.perform(put("/api/v1/products/{productId}", productId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody)
                    )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(successField.asBoolean()).isEqualTo(true);
            assertThat(errorField).isNull();
            assertThat(dataField).isNotNull();

            assertThat(dataField.get("id").asLong()).isEqualTo(productId);
            assertThat(dataField.get("name").asText()).isEqualTo(productDto.name());
            assertThat(dataField.get("barcode").asText()).isEqualTo(productDto.barcode());
            assertThat(dataField.get("photoUrl").asText()).isEqualTo(productDto.photoUrl());
            assertThat(dataField.get("cost").asInt()).isEqualTo(productDto.cost().intValue());
            assertThat(dataField.get("price").asInt()).isEqualTo(productDto.price().intValue());
        }
    }

    @Nested
    @DisplayName("제품 삭제 [DELETE]: /api/v1/products/{productId}")
    class DeleteProduct {
        @Test
        @DisplayName("제품이 존재하지 않는 경우 404 status와 error 리턴")
        void test1() throws Exception {
            // given
            Long productId = 1L;
            BDDMockito.willThrow(new ApiException(ProductErrorCode.PRODUCT_NOT_FOUND))
                    .given(productBusiness).deleteProductById(productId);

            // when
            MvcResult result = mockMvc.perform(delete("/api/v1/products/{productId}", productId))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(errorField).isNotNull();
            assertThat(dataField).isNull();

            assertThat(successField.asBoolean()).isEqualTo(false);
            assertThat(errorField.get("code").asText()).isEqualTo(ProductErrorCode.PRODUCT_NOT_FOUND.getErrorCode());
            assertThat(errorField.get("message").asText()).isEqualTo(ProductErrorCode.PRODUCT_NOT_FOUND.getErrorMessage());
        }

        @Test
        @DisplayName("정상의 경우 200 status와 api 호출 결과 리턴")
        void test1000() throws Exception {
            // given
            Long productId = 1L;
            BDDMockito.willDoNothing()
                    .given(productBusiness).deleteProductById(productId);
            // when
            MvcResult result = mockMvc.perform(delete("/api/v1/products/{productId}", productId))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            // then
            final String response = result.getResponse().getContentAsString();
            final JsonNode responseBody = objectMapper.readTree(response);

            final JsonNode successField = responseBody.get("success");
            final JsonNode errorField = responseBody.get("error");
            final JsonNode dataField = responseBody.get("data");

            assertThat(successField).isNotNull();
            assertThat(errorField).isNull();
            assertThat(dataField).isNull();

            assertThat(successField.asBoolean()).isEqualTo(true);
        }
    }
}
