package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.RegisterAdminAccountRequest;
import com.ariontour.ariontourwebsite.domain.RegisterAdminAccountResponse;

public interface RegisterAdminAccountUseCase {
    RegisterAdminAccountResponse registerAdmin(RegisterAdminAccountRequest request);
}
