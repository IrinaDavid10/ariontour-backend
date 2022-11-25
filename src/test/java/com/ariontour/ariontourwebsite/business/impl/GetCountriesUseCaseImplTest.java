package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.Country;
import com.ariontour.ariontourwebsite.domain.GetCountriesResponse;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCountriesUseCaseImplTest {
    @Mock
    private CountryRepository countryRepositoryMock;
    @InjectMocks
    private GetCountriesUseCaseImpl getCountriesUseCase;

    @Test
    void getCountries_shouldReturnAllCountriesConverted() {
        CountryEntity netherlandsEntity = CountryEntity.builder()
                .id(1L)
                .countryName("Netherlands")
                .countryCode("NL")
                .build();
        CountryEntity brazilEntity = CountryEntity.builder()
                .id(2L)
                .countryName("Brazil")
                .countryCode("BR")
                .build();
        when(countryRepositoryMock.findAll())
                .thenReturn(List.of(netherlandsEntity,brazilEntity));

        GetCountriesResponse actualResult = getCountriesUseCase.getCountries();
        Country netherlands = Country.builder()
                .id(1L)
                .country_name("Netherlands")
                .country_code("NL")
                .build();
        Country brazil = Country.builder()
                .id(2L)
                .country_name("Brazil")
                .country_code("BR")
                .build();
        GetCountriesResponse expectedResult = GetCountriesResponse.builder()
                .countries(List.of(netherlands,brazil))
                .build();
        assertEquals(expectedResult,actualResult);
        verify(countryRepositoryMock).findAll();
    }
}