package com.careerbuilder.careerbuilder.domain.attribution.service;

import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.attribution.repository.AttributionRepository;
import com.careerbuilder.careerbuilder.global.common.error.AttributionErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.careerbuilder.careerbuilder.global.common.error.AttributionErrorCode.*;

@Service
@RequiredArgsConstructor
public class AttributionService {

    private final AttributionRepository attributionRepository;

    public Attribution register(Attribution attribution) {
        return attributionRepository.save(attribution);
    }

    public List<Attribution> getAttributionList() {
        return attributionRepository.findAll();
    }

    public Attribution getAttributionById(Long attributionId) {
        return attributionRepository.findById(attributionId)
                .orElseThrow(()-> new ApiException(ATTRIBUTION_NOT_FOUND));
    }

    public void deleteAttribution(Long attributionId) {
        attributionRepository.delete(
                attributionRepository.findById(attributionId)
                        .orElseThrow(()->new ApiException(ATTRIBUTION_NOT_FOUND))
        );
    }
}
