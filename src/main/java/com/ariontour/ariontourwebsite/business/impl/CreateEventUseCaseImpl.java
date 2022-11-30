package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateEventUseCase;
import com.ariontour.ariontourwebsite.business.exception.InvalidCityException;
import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.domain.*;
import com.ariontour.ariontourwebsite.persistance.CityRepository;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.EventRepository;
import com.ariontour.ariontourwebsite.persistance.LocationRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CityEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import com.ariontour.ariontourwebsite.persistance.entity.EventEntity;
import com.ariontour.ariontourwebsite.persistance.entity.LocationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateEventUseCaseImpl implements CreateEventUseCase {
    private final LocationRepository locationRepository;
    private final EventRepository eventRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    @Override
    @Transactional
    public CreateEventResponse createEvent(CreateEventRequest request, LocalDateTime dateTime){
        LocationEntity location;
        if(locationRepository.existsByCityCityNameAndCountryCountryCode(request.getCity_name(), request.getCountry_code())){
            location = locationRepository.findByCityCityNameAndCountryCountryCode(request.getCity_name(), request.getCountry_code());

        }else {
            location = CreateLocation(request.getCity_name(),request.getCountry_code());
        }
        EventEntity savedEvent = saveNewEvent(request, dateTime, location);
        return CreateEventResponse.builder()
                .eventId(savedEvent.getId())
                .build();

    }
    private LocationEntity CreateLocation(String city_name, String country_code)
    {
        if(!countryRepository.existsByCountryCode(country_code))
        {
            throw new InvalidCountryException("COUNTRY_DOESNT_EXISTS");
        }
        if(!cityRepository.existsByCityName(city_name))
        {
            throw new InvalidCityException("CITY_DOESNT_EXISTS");
        }
        CountryEntity country = countryRepository.findByCountryCode(country_code);
        CityEntity city = cityRepository.findByCityName(city_name);
        LocationEntity newLocation = LocationEntity.builder().country(country).city(city).build();
        return locationRepository.save(newLocation);
    }
    private EventEntity saveNewEvent(CreateEventRequest request, LocalDateTime dateTime, LocationEntity location){

        EventEntity newEvent = EventEntity.builder()
                .title(request.getTitle())
                .location(location)
                .dateTime(dateTime)
                .description(request.getDescription())
                .build();

        return eventRepository.save(newEvent);
    }
}
