package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.GetCitiesUseCase;
import com.ariontour.ariontourwebsite.business.GetLocationsUseCase;
import com.ariontour.ariontourwebsite.domain.City;
import com.ariontour.ariontourwebsite.domain.GetCitiesResponse;
import com.ariontour.ariontourwebsite.domain.GetLocationsResponse;
import com.ariontour.ariontourwebsite.domain.Location;
import com.ariontour.ariontourwebsite.persistance.CityRepository;
import com.ariontour.ariontourwebsite.persistance.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetCitiesUseCaseImpl implements GetCitiesUseCase {

    private CityRepository cityRepository;
    @Override
    public GetCitiesResponse getCities(){
        List<City> cities = cityRepository.findAll()
                .stream()
                .map(CityConverter::convert)
                .toList();

        return GetCitiesResponse.builder()
                .cities(cities)
                .build();
    }

}
