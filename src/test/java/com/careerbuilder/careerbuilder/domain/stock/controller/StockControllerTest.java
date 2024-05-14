package com.careerbuilder.careerbuilder.domain.stock.controller;

import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
import com.careerbuilder.careerbuilder.domain.stock.business.StockBusiness;
import com.careerbuilder.careerbuilder.domain.stock.dto.StockDetailResponse;
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

@WebMvcTest(StockController.class)
class StockControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    StockBusiness stockBusiness;

    public StockControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[View][GET] 재고 페이지 요청")
    @Test
    void givenNothing_whenRequestingStockPage_thenReturnStockPage() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/stocks"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("stock/list"));
    }

    @DisplayName("[View][GET] 위치 ID 와 함께 재고 상세 페이지 요청")
    @Test
    void givenLocationId_whenRequestingStockDetailPage_thenReturnStockDetailPage() throws Exception {
        // given
        Long locationId = 1L;
        StockDetailResponse response = StockDetailResponse.builder().location(LocationResponse.builder().build()).build();
        given(stockBusiness.getStockListByLocationId(any()))
                .willReturn(response);

        // when & then
        mockMvc.perform(get("/stocks/{locationId}", locationId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("stock/detail"))
                .andExpect(model().attribute("stock", response));
    }
}