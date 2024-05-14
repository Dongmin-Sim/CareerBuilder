package com.careerbuilder.careerbuilder.domain.attribution.controller;

import com.careerbuilder.careerbuilder.domain.attribution.business.AttributionBusiness;
import com.careerbuilder.careerbuilder.domain.attribution.dto.AttributionResponse;
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
    public Api<AttributionResponse> register(
            @RequestBody @Valid RegisterAttributionRequest request
    ) {
        AttributionResponse response = attributionBusiness.register(request);
        return Api.OK(response);
    }

    @GetMapping
    public Api<List<AttributionResponse>> getAttributionList() {
        List<AttributionResponse> response = attributionBusiness.getAttributionList();
        return Api.OK(response);
    }

    @GetMapping("/{attributionId}")
    public Api<AttributionResponse> getAttributionById(
            @PathVariable Long attributionId
    ) {
        AttributionResponse response = attributionBusiness.getAttributionById(attributionId);
        return Api.OK(response);
    }

    @PutMapping("/{attributionId}/name")
    public Api<AttributionResponse> updateAttribution(
            @PathVariable Long attributionId,
            @RequestBody UpdateAttributionNameRequest request
    ) {
        AttributionResponse response = attributionBusiness.updateAttributionNameById(attributionId, request);
        return Api.OK(response);
    }

    @PutMapping("/{attributionId}/rank")
    public Api<AttributionResponse> partialUpdateAttribution(
            @PathVariable Long attributionId,
            @RequestBody UpdateAttributionRankRequest request
    ) {
        AttributionResponse response = attributionBusiness.updateAttributionRankById(attributionId, request);
        return Api.OK(response);
    }

    @DeleteMapping("/{attributionId}")
    public Api<AttributionResponse> deleteAttribution(
            @PathVariable Long attributionId
    ) {
        attributionBusiness.deleteAttribution(attributionId);
        return Api.OK(null);
    }
}
