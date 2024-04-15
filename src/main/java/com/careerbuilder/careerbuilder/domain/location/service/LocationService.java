package com.careerbuilder.careerbuilder.domain.location.service;

import com.careerbuilder.careerbuilder.domain.location.dto.LocationRequest;
import com.careerbuilder.careerbuilder.domain.location.entity.Location;
import com.careerbuilder.careerbuilder.domain.location.repository.LocationRepository;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.error.LocationErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> getLocationList() {
        return locationRepository.findAll();
    }

    public Location getLocationById(long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new ApiException(LocationErrorCode.LOCATION_NOT_FOUND));
    }

    public void deleteLocationById(long locationId) {
        locationRepository.delete(
                getLocationById(locationId)
        );
    }

    public boolean isExist(Long locationId) {
        return locationRepository.existsById(locationId);
    }
}
