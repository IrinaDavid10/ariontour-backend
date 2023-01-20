package com.ariontour.ariontourwebsite.business.impl;


import com.ariontour.ariontourwebsite.business.GetBookedTicketsByEventUseCase;
import com.ariontour.ariontourwebsite.domain.Booking;
import com.ariontour.ariontourwebsite.domain.Ticket;
import com.ariontour.ariontourwebsite.persistance.BookingRepository;
import com.ariontour.ariontourwebsite.persistance.TicketRepository;
import com.ariontour.ariontourwebsite.persistance.entity.BookingEntity;
import com.ariontour.ariontourwebsite.persistance.entity.TicketEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetBookedTicketsByEventUseCaseImpl implements GetBookedTicketsByEventUseCase {


    private final BookingRepository bookingRepository;

    @Override
    public List<Ticket> getBookedTicketsByEvent(Long eventId) {
        List<BookingEntity> bookings = bookingRepository.findAllByTicketEventId(eventId);
        List<Ticket> finalBookedTickets = new ArrayList<>();
        for (BookingEntity booking : bookings) {
            finalBookedTickets.add(TicketConverter.convert(booking.getTicket()));
        }
        return finalBookedTickets;
    }
}
