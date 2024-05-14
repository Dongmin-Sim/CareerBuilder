package com.careerbuilder.careerbuilder.domain.product.controller;

import com.careerbuilder.careerbuilder.domain.product.business.ProductBusiness;
import com.careerbuilder.careerbuilder.domain.product.dto.ProductResponse;
import com.careerbuilder.careerbuilder.domain.product.dto.ProductWithAttributionsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    ProductBusiness productBusiness;

    public ProductControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[View][GET] 제품 페이지 요청")
    @Test
    void givenNothing_whenRequestingProductsPage_thenReturnsProductsPage() throws Exception {
        // Given

        // When & Then
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("product/list"));
    }
    
    @DisplayName("[View][GET] 제품 ID와 함께 제품 상세 페이지 요청")
    @Test
    void givenProductId_whenRequestingProductDetailPage_thenReturnsProductDetailPage() throws Exception {
        // Given
        long productId = 1L;
        ProductWithAttributionsResponse response = ProductWithAttributionsResponse.builder()
                .productResponse(ProductResponse.builder().build())
                .build();
        given(productBusiness.getProductWithAttributionsById(any()))
                .willReturn(response);

        // When & Then
        mockMvc.perform(get("/products/"+productId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("product/detail"))
                .andExpect(model().attribute("product", response.getProductResponse()));
    }

    @DisplayName("[View][GET] 제품 추가 페이지 요청")
    @Test
    void givenNothing_whenRequestingProductAddPage_thenReturnsProductAddPage() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/products/add"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("product/addForm"))
                .andExpect(model().attributeExists("product"));
    }

    @DisplayName("[View][GET] 제품 ID와 함께 제품 상세 페이지 요청")
    @Test
    void givenProductId_whenRequestingProductEditPage_thenReturnsProductEditPage() throws Exception {
        // given
        Long productId = 1L;
        ProductWithAttributionsResponse response = ProductWithAttributionsResponse.builder()
                .productResponse(ProductResponse.builder().build())
                .build();
        given(productBusiness.getProductWithAttributionsById(any()))
                .willReturn(response);

        // when & then
        mockMvc.perform(get("/products/{productId}/edit",productId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("product/editForm"))
                .andExpect(model().attributeExists("product"));
    }

}