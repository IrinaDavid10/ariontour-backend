package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.exception.InvalidCityException;
import com.ariontour.ariontourwebsite.domain.CreateLocationRequest;
import com.ariontour.ariontourwebsite.domain.CreateLocationResponse;
import com.ariontour.ariontourwebsite.persistance.CityRepository;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.LocationRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CityEntity;
import com.ariontour.ariontourwebsite.persistance.entity.LocationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateLocationUseCaseImplTest {

    @Mock
    private CountryRepository countryRepositoryMock;
    @Mock
    private LocationRepository locationRepositoryMock;
    @Mock
    private CityRepository cityRepositoryMock;
    @InjectMocks
    private CreateLocationUseCaseImpl createLocationUseCase;

    @Test
    void createLocation_shouldReturnTheCreatedLocationId() {
        when(countryRepositoryMock.existsByCountryCode("US")).thenReturn(true);
        when(cityRepositoryMock.existsByCityName("New York")).thenReturn(true);

        CityEntity cityEntity = CityEntity.builder().cityName("New York").build();
        when(cityRepositoryMock.findByCityName("New York")).thenReturn(cityEntity);
        LocationEntity locationEntity = LocationEntity.builder().city(cityEntity).build();
        when(locationRepositoryMock.save(any(LocationEntity.class))).thenReturn(locationEntity);

        CreateLocationRequest locationRequest = CreateLocationRequest.builder().city("New York").country_code("US").build();

        CreateLocationResponse response = createLocationUseCase.createLocation(locationRequest);

        assertNotNull(response);
        assertEquals(locationEntity.getId(), response.getLocationId());

        verify(countryRepositoryMock, times(1)).existsByCountryCode("US");
        verify(cityRepositoryMock, times(1)).existsByCityName("New York");
        verify(locationRepositoryMock, times(1)).save(any(LocationEntity.class));
    }

        @Test
        void createLocation_shouldThrowInvalidCityException () {
            when(countryRepositoryMock.existsByCountryCode("US")).thenReturn(true);
            when(cityRepositoryMock.existsByCityName("New York")).thenReturn(false);

            CreateLocationRequest locationRequest = CreateLocationRequest.builder()
                    .city("New York")
                    .country_code("US")
                    .build();

            assertThrows(
                    InvalidCityException.class,
                    () -> createLocationUseCase.createLocation(locationRequest)
            );

            verify(countryRepositoryMock, times(1)).existsByCountryCode("US");
            verify(cityRepositoryMock, times(1)).existsByCityName("New York");
        }
}