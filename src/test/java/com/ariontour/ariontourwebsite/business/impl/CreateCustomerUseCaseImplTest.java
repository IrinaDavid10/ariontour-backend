package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.CreateCountryRequest;
import com.ariontour.ariontourwebsite.domain.CreateCustomerRequest;
import com.ariontour.ariontourwebsite.domain.CreateCustomerResponse;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseImplTest {
    @Mock
    private CustomerRepository customerRepositoryMock;
    @Mock
    private CountryRepository countryRepositoryMock;
    @InjectMocks
    private CreateCustomerUseCaseImpl createCustomerUseCase;

    @Test
    void createCustomer_shouldReturnTheCreateCustomerId() {
        CreateCountryRequest newCountryRequest = CreateCountryRequest.builder()
                .country_name("Greece")
                .country_code("GR")
                .build();
        CountryEntity newCountryEntity = CountryEntity.builder()
                .countryName("Greece")
                .countryCode("GR")
                .build();

        CreateCustomerRequest newCustomerRequest = CreateCustomerRequest.builder()
                .firstName("Sebastian")
                .lastName("Jonas")
                .country_code("GR")
                .build();
        CustomerEntity newCustomerEntity = CustomerEntity.builder()
                .firstName("Sebastian")
                .lastName("Jonas")
                .country(newCountryEntity)
                .build();

        when(countryRepositoryMock.existsByCountryCode("GR"))
                .thenReturn(true);

        when(countryRepositoryMock.findByCountryCode("GR"))
                .thenReturn(newCountryEntity);

        when(customerRepositoryMock.save(newCustomerEntity))
                .thenReturn(CustomerEntity.builder().id(1L).firstName("Sebastian")
                        .lastName("Jonas")
                        .country(newCountryEntity)
                        .build());


        CreateCustomerResponse actualResult = createCustomerUseCase
                .createCustomer(newCustomerRequest);
        CreateCustomerResponse expectedResult = CreateCustomerResponse.builder()
                .customerId(1L)
                .build();

        assertEquals(expectedResult,actualResult);
        verify(customerRepositoryMock).save(newCustomerEntity);
    }
}