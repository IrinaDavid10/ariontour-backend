package com.ariontour.ariontourwebsite.business;


import com.ariontour.ariontourwebsite.domain.CreateEventRequest;
import com.ariontour.ariontourwebsite.domain.CreateEventResponse;

import java.time.LocalDateTime;

public interface CreateEventUseCase {
    CreateEventResponse createEvent(CreateEventRequest request, LocalDateTime dateTime);
}

