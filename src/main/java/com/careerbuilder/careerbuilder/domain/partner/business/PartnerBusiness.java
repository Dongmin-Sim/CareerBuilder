package com.careerbuilder.careerbuilder.domain.partner.business;

import com.careerbuilder.careerbuilder.domain.partner.converter.PartnerConverter;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerRegisterRequest;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerResponse;
import com.careerbuilder.careerbuilder.domain.partner.dto.UpdatePartnerRequest;
import com.careerbuilder.careerbuilder.domain.partner.entity.Partner;
import com.careerbuilder.careerbuilder.domain.partner.service.PartnerService;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class PartnerBusiness {

    private final PartnerService partnerService;
    private final PartnerConverter partnerConverter;

    public PartnerResponse register(PartnerRegisterRequest request) {
        Partner partner = partnerConverter.toEntity(request);
        Partner saved = partnerService.save(partner);
        return partnerConverter.toResponse(saved);
    }

    public List<PartnerResponse> getPartnerList() {
        return partnerService.getPartnerList().stream()
                .map(partnerConverter::toResponse)
                .collect(Collectors.toList());
    }

    public PartnerResponse getPartnerById(Long partnerId) {
        Partner partner = partnerService.getPartnerById(partnerId);
        return partnerConverter.toResponse(partner);
    }

    @Transactional
    public PartnerResponse updatePartnerById(Long partnerId, UpdatePartnerRequest request) {
        Partner partner = partnerService.getPartnerById(partnerId);
        partner.updatePartner(request);
        return partnerConverter.toResponse(partner);
    }

    @Transactional
    public PartnerResponse partialUpdatePartnerById(Long partnerId, UpdatePartnerRequest request) {
        // TODO 부분 수정 로직 일괄 변경 예정
        Partner partner = partnerService.getPartnerById(partnerId);
        partner.updatePartner(request);
        return partnerConverter.toResponse(partner);
    }

    public PartnerResponse deletePartnerById(long partnerId) {
        partnerService.deletePartnerById(partnerId);
        return null;
    }
}
