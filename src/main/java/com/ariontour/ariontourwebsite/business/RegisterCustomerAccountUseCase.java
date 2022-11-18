package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.RegisterCustomerAccountRequest;
import com.ariontour.ariontourwebsite.domain.RegisterCustomerAccountResponse;

public interface RegisterCustomerAccountUseCase {
    RegisterCustomerAccountResponse registerCustomer(RegisterCustomerAccountRequest request);
}



