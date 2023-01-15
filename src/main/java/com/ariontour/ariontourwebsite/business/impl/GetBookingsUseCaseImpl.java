package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.GetEventsUseCase;
import com.ariontour.ariontourwebsite.domain.Customer;
import com.ariontour.ariontourwebsite.domain.Event;
import com.ariontour.ariontourwebsite.domain.GetCustomersResponse;
import com.ariontour.ariontourwebsite.domain.GetEventsResponse;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetEventsUseCaseImpl implements GetEventsUseCase {
    private EventRepository eventRepository;

    @Override
    public GetEventsResponse getEvents(){
        List<Event> events = eventRepository.findAll()
                .stream()
                .map(EventConverter::convert)
                .toList();

        return GetEventsResponse.builder()
                .events(events)
                .build();
    }
}
