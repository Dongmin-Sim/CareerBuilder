package com.careerbuilder.careerbuilder.domain.partner.controller;

import com.careerbuilder.careerbuilder.domain.partner.business.PartnerBusiness;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerResponse;
import com.careerbuilder.careerbuilder.domain.partner.entity.type.PartnerType;
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

@WebMvcTest(PartnerController.class)
public class PartnerControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    PartnerBusiness partnerBusiness;

    public PartnerControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[View][GET] 거래처 페이지 요청")
    @Test
    void givenNothing_whenRequestingPartnersPage_thenReturnsPartnersPage() throws Exception {
        // Given
        
        // When & Then
        mockMvc.perform(
                        get("/partners"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("partner/list"));
    }

    @DisplayName("[View][GET] 거래처 ID와 함께 거래처 상세 페이지 요청")
    @Test
    void givenPartnerId_whenRequestingPartnerDetailPage_thenReturnsPartnerDetailPage() throws Exception {
        // Given
        long partnerId = 1L;
        PartnerResponse response = PartnerResponse.builder().partnerType(PartnerType.IN).build();
        given(partnerBusiness.getPartnerById(any()))
                .willReturn(response);

        // When & Then
        mockMvc.perform(get("/partners/" + partnerId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("partner/detail"))
                .andExpect(model().attribute("partner", response));
    }

    @DisplayName("[View][GET] 거래처 추가 페이지 요청")
    @Test
    void givenNothing_whenRequestingPartnerAddPage_thenReturnsPartnerAddPage() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/partners/add"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("partner/addForm"))
                .andExpect(model().attributeExists("partner"));
    }

    @DisplayName("[View][GET] 거래처 ID와 함께 거래처 수정 페이지 요청")
    @Test
    void givenPartnerId_whenRequestingPartnerEditPage_thenReturnsPartnerEditPage() throws Exception {
        // given
        Long partnerId = 1L;
        PartnerResponse response = PartnerResponse.builder().build();
        given(partnerBusiness.getPartnerById(any()))
                .willReturn(response);

        // when & then
        mockMvc.perform(get("/partners/{partnerId}/edit", partnerId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("partner/editForm"))
                .andExpect(model().attribute("partner", response));
    }
}
