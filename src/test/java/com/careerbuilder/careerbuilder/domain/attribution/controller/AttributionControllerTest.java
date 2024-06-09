package com.careerbuilder.careerbuilder.domain.attribution.controller;

import com.careerbuilder.careerbuilder.domain.attribution.business.AttributionBusiness;
import com.careerbuilder.careerbuilder.domain.attribution.dto.AttributionResponseDto;
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

@WebMvcTest(AttributionController.class)
class AttributionControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    AttributionBusiness attributionBusiness;

    public AttributionControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[View][GET] 제품속성 페이지 요청")
    @Test
    void givenNothing_whenRequestingAttributionsPage_thenReturnsAttributionsPage() throws Exception {
        // Given
        
        // When & Then
        mockMvc.perform(get("/attributions"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("attribution/list"));
    }

    @DisplayName("[View][GET] 제품속성 ID와 함께 상세 페이지 요청")
    @Test
    void givenAttributionId_whenRequestingAttributionsPage_thenReturnsAttributionsPage() throws Exception {
        // Given
        given(attributionBusiness.getAttributionById(any()))
                .willReturn(AttributionResponseDto.builder().build());

        long attributionId = 1L;
        // When & Then
        mockMvc.perform(get("/attributions/" + attributionId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("attribution/detail"));
    }

    @DisplayName("[View][GET] 제품속성 추가 페이지 요청")
    @Test
    void givenNothing_whenRequestingAttributionsAddPage_thenReturnsAttributionsAddPage() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/attributions/add"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("attribution/addForm"));
    }

    @DisplayName("[View][GET] 제품속성 수정 페이지 요청")
    @Test
    void givenAttributionId_whenRegisterUpdateAttribution_thenReturnsAttributionsUpdatePage() throws Exception {
        // given
        Long attributionId = 1L;
        AttributionResponseDto response = AttributionResponseDto.builder()
                .id(attributionId)
                .build();

        given(attributionBusiness.getAttributionById(attributionId))
                .willReturn(response);

        // when & then
        mockMvc.perform(get("/attributions/{attributionId}/edit", attributionId))
                .andExpect(status().isOk())
                .andExpect(view().name("attribution/editForm"))
                .andExpect(model().attribute("attribution", response));

    }












}