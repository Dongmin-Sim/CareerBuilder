package com.careerbuilder.careerbuilder.domain.transaction.controller;

import com.careerbuilder.careerbuilder.domain.transaction.business.TransactionBusiness;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionDetailResponse;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionDetailWithProductListResponse;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    TransactionBusiness transactionBusiness;

    public TransactionControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[View][GET] 거래 페이지 요청")
    @Test
    void givenNothing_whenRequestingTransactionsPage_thenReturnsTransactionsPage() throws Exception {
        // Given

        // When & Then
        mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("transaction/list"));
    }

    @DisplayName("[View][GET] 거래 ID와 함께 거래 상세 페이지 요청")
    @Test
    void givenTransactionId_whenRequestingTransactionDetailPage_thenReturnsTransactionDetailPage() throws Exception {
        // Given
        long transactionId = 1L;
        TransactionDetailWithProductListResponse response = TransactionDetailWithProductListResponse.builder()
                .transactionType(TransactionType.IN)
                .build();

        given(transactionBusiness.getTransactionDetailWithProductListById(any()))
                .willReturn(response);

        // When & Then
        mockMvc.perform(get("/transactions/" + transactionId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("transaction/detail"))
                .andExpect(model().attribute("transaction", response));
    }

    @DisplayName("[View][GET] 거래 추가 페이지 요청")
    @Test
    void givenNothing_whenRequestingTransactionAddPage_thenReturnsTransactionAddPage() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/transactions/add"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("transaction/addForm"))
                .andExpect(model().attributeExists("transaction"));
    }

    @DisplayName("[View][GET] 거래 ID와 함께 거래 수정 페이지 요청")
    @Test
    void givenTransactionId_whenRequestingTransactionEditPage_thenReturnsTransactionEditPage() throws Exception {
        // given
        Long transactionId = 1L;
        TransactionDetailWithProductListResponse response = TransactionDetailWithProductListResponse.builder().transactionType(TransactionType.IN).build();
        given(transactionBusiness.getTransactionDetailWithProductListById(any()))
                .willReturn(response);

        // when & then
        mockMvc.perform(get("/transactions/{transactionId}/edit", transactionId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("transaction/editForm"))
                .andExpect(model().attribute("transaction", response));
    }


}