package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.*;


public interface CreateTicketUseCase {
    CreateTicketResponse createTicket(CreateTicketRequest request);
}
