package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.domain.Country;
import com.ariontour.ariontourwebsite.domain.Location;
import com.ariontour.ariontourwebsite.persistance.entity.LocationEntity;

import java.util.Optional;

public class LocationConverter {
    private LocationConverter(){

    }
    public static Location convert(LocationEntity locationEntity){
        Country country = Optional.ofNullable(locationEntity.getCountry())
                .map(CountryConverter::convert)
                .orElseThrow(() -> new InvalidCountryException("404"));

        return Location.builder()
                .id(locationEntity.getId())
                .city(locationEntity.getCity().getCityName())
                .country(country)
                .build();
    }
}
