package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.City;
import com.ariontour.ariontourwebsite.domain.GetCitiesResponse;
import com.ariontour.ariontourwebsite.persistance.CityRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CityEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCitiesUseCaseImplTest {

    @Mock
    private CityRepository cityRepositoryMock;
    @InjectMocks
    private GetCitiesUseCaseImpl getCitiesUseCase;
    @Test
    void getCities_shouldReturnAllCities() {
        CountryEntity country = CountryEntity.builder()
                .countryCode("NL")
                .countryName("Netherlands")
                .build();
        CityEntity city1Entity = CityEntity.builder()
                .id(1L)
                .cityName("Amsterdam")
                .country(country)
                .build();
        CityEntity city2Entity = CityEntity.builder()
                .id(2L)
                .cityName("Rotterdam")
                .country(country)
                .build();
        when(cityRepositoryMock.findAll())
                .thenReturn(List.of(city1Entity,city2Entity));

        GetCitiesResponse actualResult = getCitiesUseCase.getCities();

        City city1 = City.builder()
                .id(1L)
                .city_name("Amsterdam")
                .country_code(country.getCountryCode())
                .build();
        City city2 = City.builder()
                .id(2L)
                .city_name("Rotterdam")
                .country_code(country.getCountryCode())
                .build();
        GetCitiesResponse expectedResult = GetCitiesResponse.builder()
                .cities(List.of(city1,city2))
                .build();

        assertEquals(expectedResult, actualResult);
        verify(cityRepositoryMock).findAll();
    }

}
