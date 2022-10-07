package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.CreateCustomerRequest;
import com.ariontour.ariontourwebsite.domain.CreateCustomerResponse;

public interface CreateCustomerUseCase {
    CreateCustomerResponse createCustomer(CreateCustomerRequest request);
}


