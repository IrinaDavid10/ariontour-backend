package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.GetLocationsUseCase;
import com.ariontour.ariontourwebsite.domain.GetLocationsResponse;
import com.ariontour.ariontourwebsite.domain.Location;
import com.ariontour.ariontourwebsite.persistance.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetLocationsUseCaseImpl implements GetLocationsUseCase {

    private LocationRepository locationRepository;
    @Override
    public GetLocationsResponse getLocations(){
        List<Location> locations = locationRepository.findAll()
                .stream()
                .map(LocationConverter::convert)
                .toList();

        return GetLocationsResponse.builder()
                .locations(locations)
                .build();
    }

}
