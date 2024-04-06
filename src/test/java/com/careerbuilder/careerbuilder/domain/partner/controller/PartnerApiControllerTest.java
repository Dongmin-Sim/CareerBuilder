package com.careerbuilder.careerbuilder.domain.partner.controller;

import com.careerbuilder.careerbuilder.domain.partner.business.PartnerBusiness;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerRegisterRequest;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerResponse;
import com.careerbuilder.careerbuilder.domain.partner.dto.UpdatePartnerRequest;
import com.careerbuilder.careerbuilder.domain.partner.entity.type.PartnerType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.careerbuilder.careerbuilder.global.common.error.ErrorCode.OK;
import static com.careerbuilder.careerbuilder.global.common.error.ErrorCode.VALIDATION_ERROR;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PartnerApiController.class)
class PartnerApiControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper mapper;

    @MockBean
    PartnerBusiness partnerBusiness;

    public PartnerApiControllerTest(
            @Autowired MockMvc mockMvc,
            @Autowired ObjectMapper mapper) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

    @DisplayName("[POST] /api/partners: SUCC_거래처 생성 - 생성된 거래처 단건 정보를 담은 표준 API 응답 리턴")
    @Test
    void givenPartner_whenRegisterPartner_thenReturnPartnerInApiResponseOk() throws Exception {
        // Given
        PartnerRegisterRequest partner = PartnerRegisterRequest.builder()
                .partnerType(PartnerType.IN)
                .name("바른책")
                .phoneNumber("01012345678")
                .email("rightbook@gmail.com")
                .address("경기도 파주시")
                .build();
        given(partnerBusiness.register(any()))
                .willReturn(createPartnerResponse());

        // When
        mockMvc.perform(
                        post("/api/partners")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(partner))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(OK.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(OK.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(OK.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("요청 성공"))
                .andExpect(jsonPath("$.body").isMap());
        then(partnerBusiness).should().register(any());
    }

    @DisplayName("[POST] /api/partners: FAIL 거래처_생성 - 거래처 타입 필수값 누락")
    @Test
    void givenPartnerWithoutType_whenRegisterPartner_thenReturnRegisterFailInApiResponseValidationError() throws Exception{
        // Given
        PartnerRegisterRequest noTypePartnerRequest = PartnerRegisterRequest.builder()
                .name("바른책")
                .phoneNumber("01012345678")
                .email("rightbook@gmail.com")
                .address("경기도 파주시")
                .build();

        // When & Then
        mockMvc.perform(
                post("/api/partners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(noTypePartnerRequest))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(partnerBusiness).shouldHaveNoInteractions();
    }

    @DisplayName("[POST] /api/partners: FAIL 거래처_생성 - 거래처 이름 필수값 누락")
    @Test
    void givenPartnerWithoutName_whenRegisterPartner_thenReturnRegisterFailInApiResponseValidationError() throws Exception{
        // Given
        PartnerRegisterRequest noTypePartnerRequest = PartnerRegisterRequest.builder()
                .partnerType(PartnerType.IN)
                .phoneNumber("01012345678")
                .email("rightbook@gmail.com")
                .address("경기도 파주시")
                .build();

        // When & Then
        mockMvc.perform(
                        post("/api/partners")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(noTypePartnerRequest))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(partnerBusiness).shouldHaveNoInteractions();
    }

    @DisplayName("[GET] /api/partners: SUCC_거래처 리스트 조회 - 거래처 리스트 정보를 담은 표준 API 응답 출력")
    @Test
    void givenNothing_whenRetrievePartnerList_thenReturnPartnerListInApiResponseOk() throws Exception {
        // Given

        // When & Then
        mockMvc.perform(
                        get("/api/partners")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(OK.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(OK.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(OK.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("요청 성공"))
                .andExpect(jsonPath("$.body").isArray());
    }

    @DisplayName("[GET] /api/partners/{partnerId}: SUCC_거래처 단건 조회 - 거래처 단건 정보를 담은 표준 API 응답 리턴")
    @Test
    void givenPartnerId_whenRetrievePartner_thenReturnPartnerInApiResponseOk() throws Exception {
        // Given
        long partnerId = 1L;
        given(partnerBusiness.getPartnerById(partnerId))
                .willReturn(createPartnerResponse());

        // When & Then
        mockMvc.perform(
                    get("/api/partners/" + partnerId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(OK.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(OK.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(OK.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("요청 성공"))
                .andExpect(jsonPath("$.body").isMap());
        then(partnerBusiness).should().getPartnerById(partnerId);
    }

    @DisplayName("[GET] /api/partners/{partnerId}: FAIL_거래처 단건 조회 - 유효하지 않은 거래처 ID")
    @Test
    void givenPartnerId_whenRetrievePartner_thenReturnRetrieveFailInApiResponseBadRequest() throws Exception {
        // Given
        long partnerId = 0L;

        // When & Then
        mockMvc.perform(
                        get("/api/partners/" + partnerId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(partnerBusiness).shouldHaveNoInteractions();
    }

    @DisplayName("[PUT] /api/partners/{partnerId}: SUCC_거래처 변경 - 변경된 거래처 단건 정보를 담은 표준 API 응답 리턴")
    @Test
    void givenPartnerId_whenUpdatePartner_thenReturnUpdatedPartnerInApiResponseOk() throws Exception {
        // Given
        long partnerId = 1L;
        given(partnerBusiness.updatePartnerById(eq(partnerId), any()))
                .willReturn(createPartnerResponse());

        // When & Then
        mockMvc.perform(
                        put("/api/partners/" + partnerId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(createPartnerResponse()))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(OK.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(OK.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(OK.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("요청 성공"))
                .andExpect(jsonPath("$.body").isMap());
        then(partnerBusiness).should().updatePartnerById(eq(partnerId), any());
    }

    @DisplayName("[PUT] /api/partners/{partnerId}: FAIL_거래처 변경 - 유효하지 않은 거래처 ID")
    @Test
    void givenPartnerId_whenUpdatePartner_thenReturnUpdateFailInAPiResponsePartnerNotFound() throws Exception {
        // Given
        long partnerId = 0L;
        UpdatePartnerRequest updatePartner = getUpdatePartnerRequest();

        // When & Then
        mockMvc.perform(
                        put("/api/partners/" + partnerId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updatePartner))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(partnerBusiness).shouldHaveNoInteractions();
    }

    @DisplayName("[PUT] /api/partners/{partnerId}: FAIL_거래처 변경 - 필수값 누락(거래처 이름)")
    @Test
    void givenPartnerIdUpdatePartnerWithoutName_whenUpdatePartner_thenReturnUpdateFailInAPiResponsePartnerNotFound() throws Exception {
        // Given
        long partnerId = 1L;
        UpdatePartnerRequest updatePartner = UpdatePartnerRequest.builder()
                .phoneNumber("01012345678")
                .email("rightbook@gmail.com")
                .address("경기도 파주시")
                .build();

        // When & Then
        mockMvc.perform(
                        put("/api/partners/" + partnerId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updatePartner))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(partnerBusiness).shouldHaveNoInteractions();
    }

    @DisplayName("[PATCH] /api/partners/{partnerId}: SUCC_거래처 부분 변경 - 부분 변경된 거래처 단건 정보를 담은 표준 API 응답 리턴")
    @Test
    void givenPartnerId_whenPartialUpdatePartner_thenReturnUpdatedPartnerInApiResponseOk() throws Exception {
        // Given
        long partnerId = 1L;
        UpdatePartnerRequest updatePartner = getUpdatePartnerRequest();

        given(partnerBusiness.partialUpdatePartnerById(eq(partnerId), any()))
                .willReturn(createPartnerResponse());

        // When & Then
        mockMvc.perform(
                        patch("/api/partners/" + partnerId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updatePartner))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(OK.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(OK.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(OK.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("요청 성공"))
                .andExpect(jsonPath("$.body").isMap());
        then(partnerBusiness).should().partialUpdatePartnerById(eq(partnerId), any());
    }

    @DisplayName("[PATCH] /api/partners/{partnerId}: FAIL_거래처 부분 변경 - 유효하지 않은 거래처 ID")
    @Test
    void givenPartnerId_whenPartialUpdatePartner_thenReturnUpdateFailInAPiResponsePartnerNotFound() throws Exception {
        // Given
        long partnerId = 0L;
        UpdatePartnerRequest updatePartnerRequest = getUpdatePartnerRequest();

        // When & Then
        mockMvc.perform(
                        patch("/api/partners/" + partnerId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updatePartnerRequest))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(partnerBusiness).shouldHaveNoInteractions();
    }

    @DisplayName("[DELETE] /api/partners/{partnerId}: SUCC_거래처 삭제")
    @Test
    void givenPartnerId_whenDeletePartner_thenReturnDeleteSuccessInResponseApiOk() throws Exception {
        // Given
        long partnerId = 1L;
        given(partnerBusiness.deletePartnerById(partnerId))
                .willReturn(null);

        // When & Then
        mockMvc.perform(
                        delete("/api/partners/" + partnerId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(OK.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(OK.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(OK.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("요청 성공"))
                .andExpect(jsonPath("$.body").isEmpty());
        then(partnerBusiness).should().deletePartnerById(eq(partnerId));
    }

    @DisplayName("[DELETE] /api/partners/{partnerId}: FAIL_거래처 삭제 - 유효하지 않는 거래처 ID")
    @Test
    void givenPartnerId_whenDeletePartner_thenReturnDeleteFailInApiResponseBadRequest() throws Exception {
        // Given
        long partnerId = 0L;

        // When & Then
        mockMvc.perform(
                        delete("/api/partners/" + partnerId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(partnerBusiness).shouldHaveNoInteractions();
    }

    private PartnerResponse createPartnerResponse() {
        return PartnerResponse.builder()
                .id(1L)
                .partnerType(PartnerType.IN)
                .name("바른책")
                .phoneNumber("01012344459")
                .email("rightbook@gmail.com")
                .address("경기도 파주시")
                .build();
    }

    private static UpdatePartnerRequest getUpdatePartnerRequest() {
        return UpdatePartnerRequest.builder()
                .name("바른책")
                .phoneNumber("01012345678")
                .email("rightbook@gmail.com")
                .address("경기도 파주시")
                .build();
    }
}