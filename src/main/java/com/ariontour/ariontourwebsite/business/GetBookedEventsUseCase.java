package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.GetBookedEventsResponse;
import com.ariontour.ariontourwebsite.domain.GetBookedTicketsResponse;

public interface GetBookedEventsUseCase {
    GetBookedEventsResponse getBookedEvents(Long customerId);
}
