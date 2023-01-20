package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.Ticket;

import java.util.List;

public interface GetBookedTicketsByEventUseCase {

    List<Ticket> getBookedTicketsByEvent(Long eventId);
}
