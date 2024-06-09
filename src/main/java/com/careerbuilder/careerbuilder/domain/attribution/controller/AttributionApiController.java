package com.careerbuilder.careerbuilder.domain.attribution.controller;

import com.careerbuilder.careerbuilder.domain.attribution.business.AttributionBusiness;
import com.careerbuilder.careerbuilder.domain.attribution.dto.AttributionResponseDto;
import com.careerbuilder.careerbuilder.domain.attribution.dto.RegisterAttributionRequest;
import com.careerbuilder.careerbuilder.domain.attribution.dto.UpdateAttributionNameRequest;
import com.careerbuilder.careerbuilder.domain.attribution.dto.UpdateAttributionRankRequest;
import com.careerbuilder.careerbuilder.global.common.api.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attributions")
@RequiredArgsConstructor
public class AttributionApiController {

    private final AttributionBusiness attributionBusiness;

    @PostMapping
    public Api<AttributionResponseDto> register(
            @RequestBody @Valid RegisterAttributionRequest request
    ) {
        AttributionResponseDto response = attributionBusiness.register(request);
        return Api.OK(response);
    }

    @GetMapping
    public Api<List<AttributionResponseDto>> getAttributionList() {
        List<AttributionResponseDto> response = attributionBusiness.getAttributionList();
        return Api.OK(response);
    }

    @GetMapping("/{attributionId}")
    public Api<AttributionResponseDto> getAttributionById(
            @PathVariable Long attributionId
    ) {
        AttributionResponseDto response = attributionBusiness.getAttributionById(attributionId);
        return Api.OK(response);
    }

    @PutMapping("/{attributionId}/name")
    public Api<AttributionResponseDto> updateAttribution(
            @PathVariable Long attributionId,
            @RequestBody UpdateAttributionNameRequest request
    ) {
        AttributionResponseDto response = attributionBusiness.updateAttributionNameById(attributionId, request);
        return Api.OK(response);
    }

    @PutMapping("/{attributionId}/rank")
    public Api<AttributionResponseDto> partialUpdateAttribution(
            @PathVariable Long attributionId,
            @RequestBody UpdateAttributionRankRequest request
    ) {
        AttributionResponseDto response = attributionBusiness.updateAttributionRankById(attributionId, request);
        return Api.OK(response);
    }

    @DeleteMapping("/{attributionId}")
    public Api<AttributionResponseDto> deleteAttribution(
            @PathVariable Long attributionId
    ) {
        attributionBusiness.deleteAttribution(attributionId);
        return Api.OK(null);
    }
}
