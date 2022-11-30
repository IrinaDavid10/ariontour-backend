package com.ariontour.ariontourwebsite.business;


import com.ariontour.ariontourwebsite.domain.CreateCityRequest;
import com.ariontour.ariontourwebsite.domain.CreateCityResponse;

import java.time.LocalDateTime;

public interface CreateCityUseCase {
    CreateCityResponse createCity(CreateCityRequest request);
}

