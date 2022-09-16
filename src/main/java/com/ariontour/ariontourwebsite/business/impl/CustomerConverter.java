package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.Customer;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;

public class CustomerConverter {
    private CustomerConverter() {
    }

    public static Customer convert(CustomerEntity customer) {
        return Customer.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .country(customer.getCountry())
                .username(customer.getUsername())
                .build();
    }
}
