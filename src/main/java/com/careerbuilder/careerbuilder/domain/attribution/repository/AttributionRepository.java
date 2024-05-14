package com.careerbuilder.careerbuilder.domain.attribution.repository;

import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributionRepository extends JpaRepository<Attribution, Long> {

}
