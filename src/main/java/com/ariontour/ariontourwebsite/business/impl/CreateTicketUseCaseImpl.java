package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateTicketUseCase;
import com.ariontour.ariontourwebsite.business.exception.EventNotFoundException;
import com.ariontour.ariontourwebsite.domain.CreateTicketRequest;
import com.ariontour.ariontourwebsite.domain.CreateTicketResponse;
import com.ariontour.ariontourwebsite.persistance.EventRepository;
import com.ariontour.ariontourwebsite.persistance.TicketRepository;
import com.ariontour.ariontourwebsite.persistance.TicketTypeRepository;
import com.ariontour.ariontourwebsite.persistance.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;


@Service
@AllArgsConstructor
public class CreateTicketUseCaseImpl implements CreateTicketUseCase {
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;

    @Override
    @Transactional

    public CreateTicketResponse createTicket(CreateTicketRequest request){
        if (eventRepository.existsById(request.getEventId())){
            TicketEntity savedTicket = saveNewTicket(request);

                return CreateTicketResponse.builder()
                        .ticketId(savedTicket.getId())
                        .build();
        }else

            throw new EventNotFoundException();

    }

    private TicketEntity saveNewTicket(CreateTicketRequest request) {

            EventEntity event = eventRepository.findById(request.getEventId())
                    .orElseThrow(() -> new EntityNotFoundException(request.getEventId().toString()));

            TicketTypeEntity ticketType = ticketTypeRepository.getByticketType(request.getTicketType())
                .orElseThrow(() -> new EntityNotFoundException(request.getTicketType().toString()));

            TicketEntity newTicket = TicketEntity.builder()
                    .event(event)
                    .price(request.getPrice())
                    .ticketType(ticketType)
                    .build();

            return ticketRepository.save(newTicket);

    }
}
