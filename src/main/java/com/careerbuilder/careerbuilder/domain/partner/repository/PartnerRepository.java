package com.careerbuilder.careerbuilder.domain.partner.repository;

import com.careerbuilder.careerbuilder.domain.partner.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
