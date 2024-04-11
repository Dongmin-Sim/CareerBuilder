package com.careerbuilder.careerbuilder.domain.location.business;

import com.careerbuilder.careerbuilder.domain.location.dto.LocationRequest;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;

import java.util.List;

@Business
public class LocationBusiness {

    public LocationResponse register(LocationRequest request) {
        return null;
    }

    public List<LocationResponse> getLocationList() {
        return null;
    }

    public LocationResponse getLocationById(long locationId) {
        return null;
    }

    public LocationResponse updateLocationById(long locationId, LocationRequest request) {
        return null;
    }

    public LocationResponse deleteLocationById(long locationId) {
        return null;
    }
}
