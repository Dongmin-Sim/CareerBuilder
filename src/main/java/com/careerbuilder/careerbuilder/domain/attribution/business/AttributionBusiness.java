package com.careerbuilder.careerbuilder.domain.attribution.business;

import com.careerbuilder.careerbuilder.domain.attribution.converter.AttributionConverter;
import com.careerbuilder.careerbuilder.domain.attribution.dto.AttributionResponse;
import com.careerbuilder.careerbuilder.domain.attribution.dto.RegisterAttributionRequest;
import com.careerbuilder.careerbuilder.domain.attribution.dto.UpdateAttributionNameRequest;
import com.careerbuilder.careerbuilder.domain.attribution.dto.UpdateAttributionRankRequest;
import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.attribution.service.AttributionService;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class AttributionBusiness {

    private final AttributionService attributionService;
    private final AttributionConverter attributionConverter;


    public AttributionResponse register(RegisterAttributionRequest request) {
        Attribution attribution = attributionConverter.toEntity(request);
        Attribution saved = attributionService.register(attribution);
        return attributionConverter.toResponse(saved);
    }

    public List<AttributionResponse> getAttributionList() {
        List<Attribution> attributionList = attributionService.getAttributionList();
        return attributionList.stream()
                .map(attributionConverter::toResponse)
                .collect(Collectors.toList());
    }

    public AttributionResponse getAttributionById(Long attributionId) {
        Attribution attribution = attributionService.getAttributionById(attributionId);
        return attributionConverter.toResponse(attribution);
    }

    @Transactional
    public AttributionResponse updateAttributionNameById(Long attributionId, UpdateAttributionNameRequest request) {
        Attribution attribution = attributionService.getAttributionById(attributionId);
        attribution.updateName(request.getAttributionName());
        return attributionConverter.toResponse(attribution);
    }

    @Transactional
    public AttributionResponse updateAttributionRankById(Long attributionId, UpdateAttributionRankRequest request) {
        Attribution attribution = attributionService.getAttributionById(attributionId);
        attribution.updateRank(request.getRankNum());
        return attributionConverter.toResponse(attribution);
    }

    public void deleteAttribution(Long attributionId) {
        attributionService.deleteAttribution(attributionId);
    }
}
