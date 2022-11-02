package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.Customer;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerConverterTest {
    @Test
    public void CustomerConverterShouldReturnTheSameData() {
        CountryEntity romania = CountryEntity.builder()
                .id(1L)
                .countryName("Romania")
                .countryCode("RO")
                .build();

        CustomerEntity customerToBeConverted = CustomerEntity.builder()
                .id(1L)
                .firstName("Sally")
                .lastName("Jenner")
                .country(romania)
                .build();

        Customer actualCustomer = CustomerConverter.convert(customerToBeConverted);

        Customer expectedCustomer = Customer.builder()
                .id(1L)
                .firstName("Sally")
                .lastName("Jenner")
                .country(romania.getCountryCode())
                .build();

        assertEquals(expectedCustomer, actualCustomer);


    }
}