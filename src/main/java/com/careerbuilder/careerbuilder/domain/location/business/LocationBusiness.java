package com.careerbuilder.careerbuilder.domain.location.business;

import com.careerbuilder.careerbuilder.domain.location.converter.LocationConverter;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationRequest;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
import com.careerbuilder.careerbuilder.domain.location.entity.Location;
import com.careerbuilder.careerbuilder.domain.location.service.LocationService;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class LocationBusiness {

    private final LocationService locationService;
    private final LocationConverter locationConverter;

    public LocationResponse register(LocationRequest request) {
        Location location = locationConverter.toEntity(request);
        Location register = locationService.save(location);
        return locationConverter.toResponse(register);
    }

    public List<LocationResponse> getLocationList() {
        List<Location> locationList = locationService.getLocationList();
        return locationList.stream()
                .map(locationConverter::toResponse)
                .collect(Collectors.toList());
    }

    public LocationResponse getLocationById(Long locationId) {
        Location location = locationService.getLocationById(locationId);
        return locationConverter.toResponse(location);
    }

    @Transactional
    public LocationResponse updateLocationById(Long locationId, LocationRequest request) {
        Location location = locationService.getLocationById(locationId);
        location.updateLocation(request);
        return locationConverter.toResponse(location);
    }

    public LocationResponse deleteLocationById(long locationId) {
        locationService.deleteLocationById(locationId);
        return null;
    }
}
