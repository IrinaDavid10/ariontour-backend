package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.CreateCountryRequest;
import com.ariontour.ariontourwebsite.domain.CreateCountryResponse;

public interface CreateCountryUseCase {
    CreateCountryResponse createCountry(CreateCountryRequest request);
}
