package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.Country;
import com.ariontour.ariontourwebsite.domain.Customer;
import com.ariontour.ariontourwebsite.domain.GetCustomersResponse;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
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
public class GetCustomersUseCaseImplTest {

    @Mock
    private CustomerRepository customerRepositoryMock;
    @InjectMocks
    private GetCustomersUseCaseImpl getCustomersUseCase;
    @Test
    void getCustomers_shouldReturnAllCustomers() {
        CountryEntity country = CountryEntity.builder()
                .countryCode("NL")
                .countryName("Netherlands")
                .build();
        Country country1 = Country.builder()
                .country_code("NL")
                .country_name("Netherlands")
                .build();
        CustomerEntity customer1Entity = CustomerEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .country(country)
                .build();
        CustomerEntity customer2Entity = CustomerEntity.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .country(country)
                .build();
        when(customerRepositoryMock.findAll())
                .thenReturn(List.of(customer1Entity,customer2Entity));

        GetCustomersResponse actualResult = getCustomersUseCase.getCustomers();

        Customer customer1 = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .country(country1)
                .build();
        Customer customer2 = Customer.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .country(country1)
                .build();
        GetCustomersResponse expectedResult = GetCustomersResponse.builder()
                .customers(List.of(customer1,customer2))
                .build();

        assertEquals(expectedResult, actualResult);
        verify(customerRepositoryMock).findAll();
    }
}