package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.GetAllAvailableTicketsRequest;
import com.ariontour.ariontourwebsite.domain.GetAllAvailableTicketsResponse;


public interface GetTicketsUseCase {
    GetAllAvailableTicketsResponse getNumberOfAvailableTickets(GetAllAvailableTicketsRequest request);
}
