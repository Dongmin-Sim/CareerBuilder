package com.careerbuilder.careerbuilder.domain.location.service;

import com.careerbuilder.careerbuilder.domain.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public boolean isExist(Long locationId) {
        return locationRepository.existsById(locationId);
    }
}
