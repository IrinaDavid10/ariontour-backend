package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.GetCountriesUseCase;
import com.ariontour.ariontourwebsite.domain.GetCountryResponse;
import com.ariontour.ariontourwebsite.domain.Country;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetCountriesUseCaseImpl implements GetCountriesUseCase {
    private final CountryRepository countryRepository;
    @Override
    public GetCountryResponse getCountries() {
        List<Country> countries = countryRepository.findAll()
                .stream()
                .map(CountryConverter::convert)
                .toList();

        return GetCountryResponse.builder()
                .countries(countries)
                .build();
    }
}
