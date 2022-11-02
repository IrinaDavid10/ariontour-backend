package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.Country;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;

public class CountryConverter {
    private CountryConverter(){

    }

    public static Country convert(CountryEntity countryEntity){
        return Country.builder()
                .id(countryEntity.getId())
                .country_name(countryEntity.getCountryName())
                .country_code(countryEntity.getCountryCode())
                .build();
    }
}
