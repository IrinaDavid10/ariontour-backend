package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.City;
import com.ariontour.ariontourwebsite.domain.Country;
import com.ariontour.ariontourwebsite.persistance.entity.CityEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;

public class CityConverter {
    private CityConverter(){

    }

    public static City convert(CityEntity cityEntity){
        return City.builder()
                .id(cityEntity.getId())
                .city_name(cityEntity.getCityName())
                .country_code(cityEntity.getCountry().getCountryCode())
                .build();
    }
}
