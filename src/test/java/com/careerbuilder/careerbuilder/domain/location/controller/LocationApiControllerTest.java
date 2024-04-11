package com.careerbuilder.careerbuilder.domain.location.controller;

import com.careerbuilder.careerbuilder.domain.location.business.LocationBusiness;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationRequest;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LocationApiController.class)
class LocationApiControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper mapper;

    @MockBean
    LocationBusiness locationBusiness;

    public LocationApiControllerTest(
            @Autowired MockMvc mockMvc,
            @Autowired ObjectMapper mapper) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

    @DisplayName("[POST] /api/locations: SUCC_장소 생성 - 생성된 장소 단건 정보를 담은 표준 API 응답 리턴")
    @Test
    void givenLocation_whenRegisterLocation_thenReturnLocationInApiResponseOk() throws Exception{
        // Given
        LocationRequest location = createLocationRequest();
        given(locationBusiness.register(any()))
                .willReturn(createLocationResponse());

        // When
        mockMvc.perform(
                post("/api/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(location))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(OK.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(OK.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(OK.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("요청 성공"))
                .andExpect(jsonPath("$.body").isMap());
        // Then
        then(locationBusiness).should().register(any());
    }

    @DisplayName("[POST] /api/locations: FAIL_장소_생성 - 장소 이름 필수값 누락")
    @Test
    void givenLocation_whenRegisterLocation_thenReturnRegisterFailInApiResponseValidationError() throws Exception {
        // Given
        LocationRequest location = LocationRequest.builder()
                .memo("서울시 강서구")
                .build();
        given(locationBusiness.register(any()))
                .willReturn(createLocationResponse());

        // When
        mockMvc.perform(
                        post("/api/locations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(location))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(locationBusiness).shouldHaveNoInteractions();
    }

    @DisplayName("[GET] /api/locations: SUCC_장소_리스트_조회 - 조회된 장소 리스트 정보를 담은 표준 API 응답 리턴")
    @Test
    void givenNothing_whenRetrieveLocationList_thenReturnLocationListInApiResponseWithOk() throws Exception {
        // Given

        // When
        mockMvc.perform(
                        get("/api/locations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(OK.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(OK.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(OK.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("요청 성공"))
                .andExpect(jsonPath("$.body").isArray());

        // Then
        then(locationBusiness).should().getLocationList();
    }

    @DisplayName("[GET] /api/location/{locationId}: SUCC_장소_단건_조회 - 장소 단건 정보를 담은 표준 API 응답 리턴")
    @Test
    void givenLocationId_whenRetrieveLocation_thenReturnLocationInApiResponseOk() throws Exception {
        // Given
        long locationId = 1L;
        given(locationBusiness.getLocationById(locationId))
                .willReturn(createLocationResponse());

        // When & Then
        mockMvc.perform(
                        get("/api/locations/" + locationId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(OK.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(OK.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(OK.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("요청 성공"))
                .andExpect(jsonPath("$.body").isMap());
        then(locationBusiness).should().getLocationById(locationId);
    }

    @DisplayName("[GET] /api/location/{locationId}: FAIL_장소_단건_조회 - 유효하지 않은 장소 ID")
    @Test
    void givenLocationId_whenRetrieveLocation_thenReturnRetrieveFailInApiResponseBadRequest() throws Exception {
        // Given
        long locationId = 0L;

        // When & Then
        mockMvc.perform(
                        get("/api/locations/" + locationId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(locationBusiness).shouldHaveNoInteractions();
    }

    @DisplayName("[PUT] /api/locations/{locationId}: SUCC_장소_변경 - 변경된 장소 단건 정보를 담은 표준 API 응답 리턴")
    @Test
    void givenLocationId_whenUpdateLocation_thenReturnUpdatedLocationInApiResponseOk() throws Exception {
        // Given
        long locationId = 1L;
        given(locationBusiness.updateLocationById(eq(locationId), any()))
                .willReturn(createLocationResponse());

        // When & Then
        mockMvc.perform(
                        put("/api/locations/" + locationId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(createLocationRequest()))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(OK.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(OK.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(OK.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("요청 성공"))
                .andExpect(jsonPath("$.body").isMap());
        then(locationBusiness).should().updateLocationById(eq(locationId), any());
    }

    @DisplayName("[PUT] /api/locations/{locationId}: FAIL_장소 변경 - 유효하지 않은 장소 ID")
    @Test
    void givenLocationId_whenUpdateLocation_thenReturnUpdateFailInAPiResponseLocationNotFound() throws Exception {
        // Given
        long LocationId = 0L;

        // When & Then
        mockMvc.perform(
                        put("/api/locations/" + LocationId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(createLocationRequest()))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(locationBusiness).shouldHaveNoInteractions();
    }

    @DisplayName("[PUT] /api/locations/{locationId}: FAIL_장소 변경 - 필수값 누락(장소 이름)")
    @Test
    void givenLocationIdUpdateLocationWithoutName_whenUpdateLocation_thenReturnUpdateFailInAPiResponseLocationNotFound() throws Exception {
        // Given
        long LocationId = 1L;
        LocationRequest noNameUpdateLocation = LocationRequest.builder()
                .memo("서울시 강서구")
                .build();

        // When & Then
        mockMvc.perform(
                        put("/api/locations/" + LocationId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(noNameUpdateLocation))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(locationBusiness).shouldHaveNoInteractions();
    }

    @DisplayName("[DELETE] /api/locations/{locationId}: SUCC_장소 삭제")
    @Test
    void givenLocationId_whenDeleteLocation_thenReturnDeleteSuccessInResponseApiOk() throws Exception {
        // Given
        long LocationId = 1L;
        given(locationBusiness.deleteLocationById(LocationId))
                .willReturn(null);

        // When & Then
        mockMvc.perform(
                        delete("/api/locations/" + LocationId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(OK.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(OK.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(OK.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("요청 성공"))
                .andExpect(jsonPath("$.body").isEmpty());
        then(locationBusiness).should().deleteLocationById(eq(LocationId));
    }

    @DisplayName("[DELETE] /api/locations/{locationId}: FAIL_장소 삭제 - 유효하지 않는 장소 ID")
    @Test
    void givenLocationId_whenDeleteLocation_thenReturnDeleteFailInApiResponseBadRequest() throws Exception {
        // Given
        long LocationId = 0L;

        // When & Then
        mockMvc.perform(
                        delete("/api/locations/" + LocationId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.resultCode").value(VALIDATION_ERROR.getErrorCode()))
                .andExpect(jsonPath("$.result.resultType").value(VALIDATION_ERROR.getErrorType().name()))
                .andExpect(jsonPath("$.result.resultMessage").value(VALIDATION_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.result.description").value("유효하지 않은 요청입니다."))
                .andExpect(jsonPath("$.body").isEmpty());
        then(locationBusiness).shouldHaveNoInteractions();
    }

    private static LocationRequest createLocationRequest() {
        return  LocationRequest.builder()
                .name("서부 창고")
                .memo("서울시 강서구")
                .build();
    }

    private static LocationResponse createLocationResponse() {
        return LocationResponse.builder()
                .id(1L)
                .name("서부 창고")
                .memo("서울시 강서구")
                .build();
    }
}