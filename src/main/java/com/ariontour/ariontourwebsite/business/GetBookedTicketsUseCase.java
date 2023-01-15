package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.GetBookedTicketsResponse;

public interface GetBookedTicketsUseCase {
    GetBookedTicketsResponse getBookedTickets(Long eventId, Long customerId);
}
