package com.careerbuilder.careerbuilder.domain.partner.service;

import com.careerbuilder.careerbuilder.domain.partner.entity.Partner;
import com.careerbuilder.careerbuilder.domain.partner.repository.PartnerRepository;
import com.careerbuilder.careerbuilder.global.common.error.PartnerErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;

    public Partner save(Partner partner) {
        return partnerRepository.save(partner);
    }

    public List<Partner> getPartnerList() {
        return partnerRepository.findAll();
    }

    public Partner getPartnerById(long partnerId) {
        return partnerRepository.findById(partnerId)
                .orElseThrow(() -> new ApiException(PartnerErrorCode.PARTNER_NOT_FOUND));
    }

    public void deletePartnerById(long partnerId) {
        partnerRepository.delete(
                getPartnerById(partnerId)
        );
    }
}
