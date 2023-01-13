package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.DeleteCustomerRequest;
import com.ariontour.ariontourwebsite.domain.DeleteUserRequest;

public interface DeleteUserUseCase {

    boolean deleteUser(Long id);
}
