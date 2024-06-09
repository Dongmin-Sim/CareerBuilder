package com.careerbuilder.careerbuilder.domain.location.controller;

import com.careerbuilder.careerbuilder.domain.location.business.LocationBusiness;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationRequest;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
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

@WebMvcTest(LocationController.class)
class LocationControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    LocationBusiness locationBusiness;

    public LocationControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[View][GET] 위치 페이지 요청")
    @Test
    void givenNothing_whenRequestingLocationsPage_thenReturnsLocationsPage() throws Exception {
        // Given

        // When & Then
        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("location/list"));
    }

    @DisplayName("[View][GET] 위치 ID와 함께 상세 페이지 요청")
    @Test
    void givenLocationId_whenRequestingLocationDetailPage_thenReturnsLocationDetailPage() throws Exception {
        // Given
        long locationId = 1L;
        LocationResponse response = LocationResponse.builder().build();
        given(locationBusiness.getLocationById(any()))
                .willReturn(response);

        // When & Then
        mockMvc.perform(get("/locations/" + locationId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("location/detail"))
                .andExpect(model().attribute("location", response));
    }

    @DisplayName("[View][GET] 위치 추가 페이지 요청")
    @Test
    void givenNothing_whenRequestingLocationAddPage_thenReturnsLocationAddPage() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/locations/add"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("location", LocationRequest.builder().build()))
                .andExpect(view().name("location/addForm"));

    }

    @DisplayName("[View][GET] 위치 ID와 함께 위치 수정 페이지 요청")
    @Test
    void givenLocationId_whenRequestingLocationEditPage_thenReturnsLocationEditPage() throws Exception {
        long locationId = 1L;
        LocationResponse response = LocationResponse.builder().build();
        given(locationBusiness.getLocationById(any()))
                .willReturn(response);

        mockMvc.perform(get("/locations/{locationId}/edit", locationId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("location/editForm"))
                .andExpect(model().attribute("location", response));
    }
}