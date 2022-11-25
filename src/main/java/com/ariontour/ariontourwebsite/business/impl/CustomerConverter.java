package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.domain.Country;
import com.ariontour.ariontourwebsite.domain.Customer;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class CustomerConverter {
    private CustomerConverter() {
    }

    public static Customer convert(CustomerEntity customer) {
        Country country = Optional.ofNullable(customer.getCountry())
                .map(CountryConverter::convert)
                .orElseThrow(() -> new InvalidCountryException("404"));

        return Customer.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .country(country)
                .build();
    }
}
