package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateTicketUseCase;
import com.ariontour.ariontourwebsite.business.exception.EventNotFoundException;
import com.ariontour.ariontourwebsite.domain.CreateTicketRequest;
import com.ariontour.ariontourwebsite.domain.CreateTicketResponse;
import com.ariontour.ariontourwebsite.persistance.BookingRepository;
import com.ariontour.ariontourwebsite.persistance.EventRepository;
import com.ariontour.ariontourwebsite.persistance.TicketRepository;
import com.ariontour.ariontourwebsite.persistance.TicketTypeRepository;
import com.ariontour.ariontourwebsite.persistance.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CreateTicketUseCaseImpl implements CreateTicketUseCase {
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public CreateTicketResponse createTicket(CreateTicketRequest request){

        if (eventRepository.existsById(request.getEventId())){
            List<TicketEntity> savedTickets = saveNewTickets(request);

            List<Long> ticketIds = new ArrayList<>();

            for (TicketEntity ticket : savedTickets)
            {
                ticketIds.add(ticket.getId());
            }
                return CreateTicketResponse.builder()
                        .ticketId(ticketIds)
                        .build();
        }else

            throw new EventNotFoundException();

    }

    private List<TicketEntity> saveNewTickets(CreateTicketRequest request) {

        EventEntity event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new EntityNotFoundException(request.getEventId().toString()));

        TicketTypeEntity ticketType = ticketTypeRepository.getByticketType(request.getTicketType())
                .orElseThrow(() -> new EntityNotFoundException(request.getTicketType().toString()));

        List<TicketEntity> newTickets = new ArrayList<>();
        for (int i = 0; i < request.getNumberOfTickets(); i++) {
            TicketEntity newTicket = TicketEntity.builder()
                    .event(event)
                    .price(request.getPrice())
                    .ticketType(ticketType)
                    .build();
            newTickets.add(newTicket);
        }
        return ticketRepository.saveAll(newTickets);
    }

}
