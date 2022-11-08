package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.CreateCountryRequest;
import com.ariontour.ariontourwebsite.domain.CreateCountryResponse;
import com.ariontour.ariontourwebsite.domain.CreateCustomerRequest;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateCountryUseCaseImplTest {
    @Mock
    private CountryRepository countryRepositoryMock;
    @InjectMocks
    private CreateCountryUseCaseImpl createCountryUseCase;

    @Test
    void createCountry_shouldReturnTheCreatedCountryId() {
        CreateCountryRequest newCountryRequest = CreateCountryRequest.builder()
                                                    .country_name("Netherlands")
                                                    .country_code("NL")
                                                    .build();
        CountryEntity newCountryEntity = CountryEntity.builder()
                                            .countryName("Netherlands")
                                            .countryCode("NL")
                                            .build();
        when(countryRepositoryMock.save(newCountryEntity))
                .thenReturn(CountryEntity.builder()
                        .countryName(newCountryEntity.getCountryName())
                        .countryCode(newCountryEntity.getCountryCode())
                        .id(1L)
                        .build());
        CreateCountryResponse actualResult = createCountryUseCase.createCountry(newCountryRequest);
        CreateCountryResponse expectedResult = CreateCountryResponse.builder()
                                                        .countryId(actualResult.getCountryId())
                                                        .build();
        assertEquals(expectedResult,actualResult);
        verify(countryRepositoryMock).save(newCountryEntity);
    }
}