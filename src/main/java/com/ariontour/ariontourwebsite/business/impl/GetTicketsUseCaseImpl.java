package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.GetTicketsUseCase;
import com.ariontour.ariontourwebsite.domain.GetAllAvailableTicketsRequest;
import com.ariontour.ariontourwebsite.domain.GetAllAvailableTicketsResponse;
import com.ariontour.ariontourwebsite.persistance.BookingRepository;
import com.ariontour.ariontourwebsite.persistance.TicketRepository;
import com.ariontour.ariontourwebsite.persistance.entity.BookingEntity;
import com.ariontour.ariontourwebsite.persistance.entity.TicketEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class GetTicketsUseCaseImpl implements GetTicketsUseCase {
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;
    @Override
    @Transactional
    public GetAllAvailableTicketsResponse getNumberOfAvailableTickets(GetAllAvailableTicketsRequest request){
        List<BookingEntity> bookings = bookingRepository.findAllByTicketEventId(request.getEventId());
        List<TicketEntity> tickets = ticketRepository.findAllByEventId(request.getEventId());
        List<BookingEntity> bookingsByTicketType = new ArrayList<>();
        List<TicketEntity> ticketsByTicketType = new ArrayList<>();
        for( BookingEntity booking : bookings)
        {
            if(booking.getTicket().getTicketType().getTicketType() == request.getTicketType())
                bookingsByTicketType.add(booking);
        }
        for( TicketEntity ticket : tickets)
        {
            if(ticket.getTicketType().getTicketType() == request.getTicketType())
                ticketsByTicketType.add(ticket);
        }
        System.out.println("BOOKINGS: " + bookings.size() +" BOOKINGS BY TYPE: " + bookingsByTicketType.size() + " TICKETS: "
                + tickets.size()+" TICKETS BY TYPE: " + ticketsByTicketType.size() + " OF TYPE: " + request.getTicketType());
        long nrOfTickets = Math.abs(ticketsByTicketType.size() - bookingsByTicketType.size());

        return GetAllAvailableTicketsResponse.builder().numberOfAvailableTickets(nrOfTickets).build();
    }
}
