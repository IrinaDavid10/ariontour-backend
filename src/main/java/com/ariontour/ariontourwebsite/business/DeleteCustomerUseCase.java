package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.DeleteCustomerRequest;

public interface DeleteCustomerUseCase {
    boolean deleteCustomer(DeleteCustomerRequest request);
}
