package com.careerbuilder.careerbuilder.domain.partner.business;

import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerRegisterRequest;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerResponse;
import com.careerbuilder.careerbuilder.domain.partner.dto.UpdatePartnerRequest;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business
@RequiredArgsConstructor
public class PartnerBusiness {

    public PartnerResponse register(PartnerRegisterRequest request) {
        return null;
    }

    public List<PartnerResponse> getPartnerList() {
        return null;
    }

    public PartnerResponse getPartnerById(long partnerId) {
        return null;
    }

    public PartnerResponse updatePartnerById(long partnerId, UpdatePartnerRequest request) {
        return null;
    }

    public PartnerResponse partialUpdatePartnerById(Long partnerId, UpdatePartnerRequest request) {
        return null;
    }

    public PartnerResponse deletePartnerById(long partnerId) {
        return null;
    }
}
