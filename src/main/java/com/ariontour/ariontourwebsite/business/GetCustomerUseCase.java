package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.Customer;

import java.util.Optional;

public interface GetCustomerUseCase {
    Optional<Customer> getCustomer(long customerId);
}
