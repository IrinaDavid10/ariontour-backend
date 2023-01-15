package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.GetBookedTicketsResponse;
import com.ariontour.ariontourwebsite.domain.GetEventsResponse;

public interface GetBookingsUseCase {
    GetBookedTicketsResponse getBookings(Long eventId, Long customerId);
}
