package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.GetEventUseCase;
import com.ariontour.ariontourwebsite.domain.Event;
import com.ariontour.ariontourwebsite.persistance.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetEventUseCaseImpl implements GetEventUseCase {
    private EventRepository eventRepository;

    @Override
    public Optional<Event> getEvent(long eventId){
        return eventRepository.findById(eventId).map(EventConverter::convert);
    }
}
