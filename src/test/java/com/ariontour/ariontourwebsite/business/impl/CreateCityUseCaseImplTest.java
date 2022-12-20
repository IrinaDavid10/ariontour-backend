package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.exception.InvalidCityException;
import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.domain.CreateCityRequest;
import com.ariontour.ariontourwebsite.domain.CreateCityResponse;
import com.ariontour.ariontourwebsite.persistance.CityRepository;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CityEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCityUseCaseImplTest {
    @Mock
    private CityRepository cityRepositoryMock;
    @Mock
    private CountryRepository countryRepositoryMock;
    @InjectMocks
    private CreateCityUseCaseImpl createCityUseCase;

    @Test
    void createCity_shouldReturnTheCreatedCityId() {
        CreateCityRequest newCityRequest = CreateCityRequest.builder()
                .city_name("Amsterdam")
                .country_code("NL")
                .build();
        CountryEntity newCountryEntity = CountryEntity.builder()
                .countryName("Netherlands")
                .countryCode("NL")
                .build();
        CityEntity newCityEntity = CityEntity.builder()
                .cityName("Amsterdam")
                .country(newCountryEntity)
                .build();

        when(countryRepositoryMock.existsByCountryCode("NL"))
                .thenReturn(true);

        when(countryRepositoryMock.findByCountryCode("NL"))
                .thenReturn(newCountryEntity);

        when(cityRepositoryMock.save(newCityEntity))
                .thenReturn(CityEntity.builder()
                        .cityName(newCityEntity.getCityName())
                        .country(newCityEntity.getCountry())
                        .id(1L)
                        .build());

        CreateCityResponse actualResult = createCityUseCase.createCity(newCityRequest);
        CreateCityResponse expectedResult = CreateCityResponse.builder()
                .cityId(actualResult.getCityId())
                .build();

        assertEquals(expectedResult, actualResult);

        verify(countryRepositoryMock).existsByCountryCode("NL");
        verify(countryRepositoryMock).findByCountryCode("NL");
        verify(cityRepositoryMock).save(newCityEntity);
    }

    @Test
    void createCity_shouldThrowInvalidCityExceptionIfCityAlreadyExists() {
        CreateCityRequest newCityRequest = CreateCityRequest.builder()
                .city_name("Amsterdam")
                .country_code("NL")
                .build();
        CountryEntity newCountryEntity = CountryEntity.builder()
                .countryName("Netherlands")
                .countryCode("NL")
                .build();
        CityEntity newCityEntity = CityEntity.builder()
                .cityName("Amsterdam")
                .country(newCountryEntity)
                .build();

        when(cityRepositoryMock.existsByCityName("Amsterdam")).thenReturn(true);

        assertThrows(InvalidCityException.class, () -> createCityUseCase.createCity(newCityRequest));
        verify(cityRepositoryMock).existsByCityName("Amsterdam");
        verify(countryRepositoryMock, never()).existsByCountryCode("NL");
        verify(countryRepositoryMock, never()).findByCountryCode("NL");
        verify(cityRepositoryMock, never()).save(newCityEntity);
    }

    @Test
    void createCity_shouldThrowInvalidCountryExceptionIfCountryDoesNotExist() {
        CreateCityRequest newCityRequest = CreateCityRequest.builder()
                .city_name("Amsterdam")
                .country_code("NL")
                .build();
        CountryEntity newCountryEntity = CountryEntity.builder()
                .countryName("Netherlands")
                .countryCode("NL")
                .build();
        CityEntity newCityEntity = CityEntity.builder()
                .cityName("Amsterdam")
                .country(newCountryEntity)
                .build();

        when(countryRepositoryMock.existsByCountryCode("NL")).thenReturn(false);

        assertThrows(InvalidCountryException.class, () -> createCityUseCase.createCity(newCityRequest));
        verify(cityRepositoryMock).existsByCityName("Amsterdam");
        verify(countryRepositoryMock).existsByCountryCode("NL");
        verify(countryRepositoryMock, never()).findByCountryCode("NL");
        verify(cityRepositoryMock, never()).save(newCityEntity);
    }


}
