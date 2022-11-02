package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateCountryUseCase;
import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.domain.CreateCountryRequest;
import com.ariontour.ariontourwebsite.domain.CreateCountryResponse;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCountryUseCaseImpl implements CreateCountryUseCase {
    private final CountryRepository countryRepository;

    @Transactional
    @Override
    public CreateCountryResponse createCountry(CreateCountryRequest request) {

        if (countryRepository.existsByCountryCode(request.getCountry_code())) {
            throw new InvalidCountryException("CODE_DUPLICATED");
        }
        CountryEntity newCountry = CountryEntity.builder()
                .countryCode(request.getCountry_code())
                .countryName(request.getCountry_name())
                .build();
        CountryEntity savedCountry = countryRepository.save(newCountry);

        return CreateCountryResponse.builder()
                .countryId(savedCountry.getId())
                .build();
    }

}

