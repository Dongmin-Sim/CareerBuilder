package com.careerbuilder.careerbuilder.domain.location.repository;

import com.careerbuilder.careerbuilder.domain.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    
}
