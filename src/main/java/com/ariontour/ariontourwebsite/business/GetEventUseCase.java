package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.Event;

import java.util.Optional;

public interface GetEventUseCase {
    Optional<Event> getEvent(long eventId);
}
