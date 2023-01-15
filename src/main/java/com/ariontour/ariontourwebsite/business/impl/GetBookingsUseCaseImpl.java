package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.GetBookedEventsUseCase;
import com.ariontour.ariontourwebsite.business.GetBookedTicketsUseCase;
import com.ariontour.ariontourwebsite.domain.*;
import com.ariontour.ariontourwebsite.persistance.BookingRepository;
import com.ariontour.ariontourwebsite.persistance.EventRepository;
import com.ariontour.ariontourwebsite.persistance.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetBookingsUseCaseImpl implements GetBookedTicketsUseCase, GetBookedEventsUseCase {

    private BookingRepository bookingRepository;
    @Override
    public GetBookedTicketsResponse getBookedTickets(Long eventId, Long customerId){
        List<Booking> bookings = bookingRepository.findAllByTicketEventIdAndCustomerId(eventId,customerId)
                .stream()
                .map(BookingConverter::convert)
                .toList();
        List<Ticket> tickets = bookings.stream()
                .map(Booking::getTicket)
                .toList();

        return GetBookedTicketsResponse.builder()
                .tickets(tickets)
                .build();
    }

    @Override
    public GetBookedEventsResponse getBookedEvents(Long customerId) {
        List<Event> events = bookingRepository.findAllEventsInBookingByCustomerId(customerId)
                .stream()
                .map(EventConverter::convert)
                .toList();

        return GetBookedEventsResponse.builder()
                .events(events)
                .build();
    }
}
