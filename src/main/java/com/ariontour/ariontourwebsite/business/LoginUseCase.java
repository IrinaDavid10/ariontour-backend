package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.LoginRequest;
import com.ariontour.ariontourwebsite.domain.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
