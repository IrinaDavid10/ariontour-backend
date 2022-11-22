package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateEventUseCase;
import com.ariontour.ariontourwebsite.business.CreateLocationUseCase;
import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.business.exception.InvalidLocationException;
import com.ariontour.ariontourwebsite.domain.*;
import com.ariontour.ariontourwebsite.persistance.EventRepository;
import com.ariontour.ariontourwebsite.persistance.LocationRepository;
import com.ariontour.ariontourwebsite.persistance.entity.EventEntity;
import com.ariontour.ariontourwebsite.persistance.entity.LocationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateEventUseCaseImpl implements CreateEventUseCase {
    private final LocationRepository locationRepository;
    private final EventRepository eventRepository;
    @Override
    @Transactional
    public CreateEventResponse createEvent(CreateEventRequest request, LocalDateTime dateTime){
        if(locationRepository.existsById(request.getLocationId())){
            EventEntity savedEvent = saveNewEvent(request, dateTime);
            return CreateEventResponse.builder()
                    .eventId(savedEvent.getId())
                    .build();
        }else

        throw new InvalidLocationException("Location with id "+request.getLocationId()+" does not exist.");

    }
    private EventEntity saveNewEvent(CreateEventRequest request, LocalDateTime dateTime){
        LocationEntity location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException(request.getLocationId().toString()));
        EventEntity newEvent = EventEntity.builder()
                .title(request.getTitle())
                .location(location)
                .dateTime(dateTime)
                .build();

        return eventRepository.save(newEvent);
    }
}
