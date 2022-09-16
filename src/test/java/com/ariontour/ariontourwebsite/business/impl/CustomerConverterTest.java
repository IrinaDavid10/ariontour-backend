package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.Customer;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerConverterTest {
    @Test
    public void CustomerConverterShouldReturnTheSameData(){
        CustomerEntity customer = CustomerEntity.builder().id(1L).firstName("Sally").lastName("Jenner").country("USA").username("sallyj").build();
        Customer convertedCustomer = CustomerConverter.convert(customer);
        assertEquals(customer.getId(),convertedCustomer.getId());
        assertEquals(customer.getFirstName(),convertedCustomer.getFirstName());
        assertEquals(customer.getLastName(), convertedCustomer.getLastName());
        assertEquals(customer.getCountry(), convertedCustomer.getCountry());
        assertEquals(customer.getUsername(), convertedCustomer.getUsername()+"ari");
    }

}