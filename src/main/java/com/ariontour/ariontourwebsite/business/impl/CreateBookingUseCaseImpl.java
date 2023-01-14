package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateBookingUseCase;
import com.ariontour.ariontourwebsite.business.exception.EventNotFoundException;
import com.ariontour.ariontourwebsite.business.exception.TicketNotFoundException;
import com.ariontour.ariontourwebsite.business.exception.UsernameNotFoundException;
import com.ariontour.ariontourwebsite.domain.*;
import com.ariontour.ariontourwebsite.persistance.*;
import com.ariontour.ariontourwebsite.persistance.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateBookingUseCaseImpl implements CreateBookingUseCase {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    @Override
    @Transactional
    public CreateBookingResponse createBooking(CreateBookingRequest request){
        if(customerRepository.existsById(request.getCustomerId()) && eventRepository.existsById(request.getEventId())){
            List<TicketEntity> allAvailableTickets = ticketRepository.findAllByEventId(request.getEventId());
            List<BookingEntity> currentBookings = bookingRepository.findAllByTicketEventId(request.getEventId());
            TicketTypeEntity ticketType = ticketTypeRepository.findByTicketType(request.getTicketType());

            Long numberOfTicketsAvailable= allAvailableTickets.stream().filter(ticket -> ticket.getTicketType().equals(ticketType)).count();
            Long numberOfTicketsUnavailable = currentBookings.stream().filter(booking -> booking.getTicket().getTicketType().equals(ticketType)).count();

            if (numberOfTicketsAvailable > numberOfTicketsUnavailable &&numberOfTicketsAvailable - numberOfTicketsUnavailable>= request.getNumberOfBookings()) {
                List<BookingEntity> savedBookings = saveNewBooking(request);
                List<Long> savedBookingsIds = new ArrayList<>();
                for (BookingEntity booking : savedBookings) {
                    savedBookingsIds.add(booking.getId());
                }
                return CreateBookingResponse.builder()
                        .bookingIds(savedBookingsIds)
                        .build();

            }else {
            throw new TicketNotFoundException();
            }
        }else
            if(!eventRepository.existsById(request.getEventId()))
                throw new EventNotFoundException();
            throw new UsernameNotFoundException();

    }

    private List<BookingEntity> saveNewBooking(CreateBookingRequest request) {
        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException(request.getCustomerId().toString()));
        List<BookingEntity> newBookings = new ArrayList<>();
        TicketTypeEntity ticketType = ticketTypeRepository.findByTicketType(request.getTicketType());
        List<TicketEntity> Tickets = ticketRepository.findAllByTicketTypeAndEventId(ticketType, request.getEventId());
        for(int i=0; i< request.getNumberOfBookings(); i++) {
            BookingEntity newBooking = BookingEntity.builder()
                    .customer(customer)
                    .dateTime(LocalDateTime.now())
                    .ticket(Tickets.get(i))
                    .build();
            newBookings.add(newBooking);
        }
        return bookingRepository.saveAll(newBookings);
    }

}
