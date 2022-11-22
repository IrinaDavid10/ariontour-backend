package com.ariontour.ariontourwebsite.business;


import com.ariontour.ariontourwebsite.domain.CreateLocationRequest;
import com.ariontour.ariontourwebsite.domain.CreateLocationResponse;

public interface CreateLocationUseCase {
    CreateLocationResponse createLocation(CreateLocationRequest request);
}

