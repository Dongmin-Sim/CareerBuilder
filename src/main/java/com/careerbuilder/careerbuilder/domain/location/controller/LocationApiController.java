package com.careerbuilder.careerbuilder.domain.location.controller;

import com.careerbuilder.careerbuilder.domain.location.business.LocationBusiness;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationRequest;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
import com.careerbuilder.careerbuilder.global.common.api.Api;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationApiController {

    private final LocationBusiness locationBusiness;

    /**
     * 장소 생성
     * @param request 장소 생성 요청 객체
     * @return 생성된 장소 단건 정보를 담은 표준 API 응답
     */
    @PostMapping
    public Api<LocationResponse> register(
            @RequestBody @Valid
            LocationRequest request
    ) {
        var response = locationBusiness.register(request);
        return Api.OK(response);
    }

    /**
     * 장소 리스트 조회
     *
     * @return 조회된 장소 리스트 정보를 담은 표준 API 응답
     */
    @GetMapping
    public Api<List<LocationResponse>> getLocationList() {
        var response = locationBusiness.getLocationList();
        return Api.OK(response);
    }

    /**
     * 장소 단건 조회
     * @param locationId 장소 ID
     * @return Id로 조회된 장소 단건 정보를 담은 표준 API 응답
     */
    @GetMapping("/{locationId}")
    public Api<LocationResponse> getLocationById(
            @PathVariable @Positive Long locationId
    ) {
        var response = locationBusiness.getLocationById(locationId);
        return Api.OK(response);
    }

    /**
     * 장소 변경
     *
     * @param locationId 장소 ID
     * @param request    장소 변경 데이터를 담은 요청 정보
     * @return 변경된 장소 단건 정보를 담은 표준 API 응답
     */
    @PutMapping("/{locationId}")
    public Api<LocationResponse> updateLocationById(
            @PathVariable @Positive Long locationId,
            @RequestBody @Valid LocationRequest request
    ) {
        var response = locationBusiness.updateLocationById(locationId, request);
        return Api.OK(response);
    }

    /**
     * 장소 삭제
     * @param locationId 장소 ID
     * @return 성공 여부를 알리는 표준 API 응답
     */
    @DeleteMapping("/{locationId}")
    public Api<LocationResponse> deleteLocation(
            @PathVariable @Positive Long locationId
    ) {
        locationBusiness.deleteLocationById(locationId);
        return Api.OK(null);
    }

}
