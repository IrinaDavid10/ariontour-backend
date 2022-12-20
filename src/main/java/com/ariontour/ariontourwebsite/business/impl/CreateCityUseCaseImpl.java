package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateCityUseCase;
import com.ariontour.ariontourwebsite.business.exception.InvalidCityException;
import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.domain.CreateCityRequest;
import com.ariontour.ariontourwebsite.domain.CreateCityResponse;
import com.ariontour.ariontourwebsite.persistance.CityRepository;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CityEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCityUseCaseImpl implements CreateCityUseCase {
    private final CityRepository cityRepository;

    private final CountryRepository countryRepository;

    @Transactional
    @Override
    public CreateCityResponse createCity(CreateCityRequest request) {


        if (cityRepository.existsByCityName(request.getCity_name())) {
            throw new InvalidCityException("CITY_DUPLICATED");
        }

        if (!countryRepository.existsByCountryCode(request.getCountry_code())) {
            throw new InvalidCountryException("COUNTRY_DOESNT_EXISTS");
        }
        CountryEntity country = countryRepository.findByCountryCode(request.getCountry_code());
        System.out.println(country.getCountryName());
        CityEntity newCity = CityEntity.builder()
                .cityName(request.getCity_name())
                .country(country)
                .build();

        CityEntity savedCity = cityRepository.save(newCity);

        return CreateCityResponse.builder()
                .cityId(savedCity.getId())
                .build();
    }

}

