package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.Country;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryConverterTest {
    @Test
    public void CountryConverterShouldReturnSameData(){
        CountryEntity romania = CountryEntity.builder()
                .id(1L)
                .countryName("Romania")
                .countryCode("RO").build();
        Country actual = CountryConverter.convert(romania);
        Country expected = Country.builder()
                .id(1L)
                .country_name("Romania")
                .country_code("RO")
                .build();
        assertEquals(expected, actual);

    }
}
