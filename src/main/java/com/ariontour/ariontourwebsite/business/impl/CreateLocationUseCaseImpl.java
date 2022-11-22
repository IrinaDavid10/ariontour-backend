package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateLocationUseCase;
import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.domain.CreateCustomerRequest;
import com.ariontour.ariontourwebsite.domain.CreateCustomerResponse;
import com.ariontour.ariontourwebsite.domain.CreateLocationRequest;
import com.ariontour.ariontourwebsite.domain.CreateLocationResponse;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.LocationRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import com.ariontour.ariontourwebsite.persistance.entity.LocationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CreateLocationUseCaseImpl implements CreateLocationUseCase {
    private final CountryRepository countryRepository;
    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public CreateLocationResponse createLocation(CreateLocationRequest request){
        if(countryRepository.existsByCountryCode(request.getCountry_code())){
            LocationEntity savedLocation = saveNewLocation(request);
            return CreateLocationResponse.builder()
                    .locationId(savedLocation.getId())
                    .build();
        }

        throw new InvalidCountryException("Country with code "+request.getCountry_code()+" does not exist.");

    }
    private LocationEntity saveNewLocation(CreateLocationRequest request){
        LocationEntity newLocation = LocationEntity.builder()
                .city(request.getCity())
                .country(countryRepository.findByCountryCode(request.getCountry_code()))
                .build();
        return locationRepository.save(newLocation);
    }
}
