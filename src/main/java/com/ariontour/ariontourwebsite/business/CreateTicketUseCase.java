package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.CreateEventResponse;
import com.ariontour.ariontourwebsite.domain.CreateTicketRequest;
import com.ariontour.ariontourwebsite.domain.CreateTicketResponse;


public interface CreateTicketUseCase {
    CreateTicketResponse createTicket(CreateTicketRequest request);
}
