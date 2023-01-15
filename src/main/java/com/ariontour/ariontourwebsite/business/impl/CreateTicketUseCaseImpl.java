package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateTicketUseCase;
import com.ariontour.ariontourwebsite.business.exception.EventNotFoundException;
import com.ariontour.ariontourwebsite.business.exception.TicketNotFoundException;
import com.ariontour.ariontourwebsite.domain.CreateTicketRequest;
import com.ariontour.ariontourwebsite.domain.CreateTicketsRequest;
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


@Service
@AllArgsConstructor
public class CreateTicketUseCaseImpl implements CreateTicketUseCase {
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public CreateTicketResponse createTicket(CreateTicketsRequest requests ){

        if (eventRepository.existsById(requests.getEventId())){
            if(requests.getRequests().size() == 3) {
                List<TicketEntity> savedTicketsBronze = saveNewTickets(requests.getEventId(), requests.getRequests().get(0));
                List<TicketEntity> savedTicketsSilver = saveNewTickets(requests.getEventId(), requests.getRequests().get(1));
                List<TicketEntity> savedTicketsGold = saveNewTickets(requests.getEventId(), requests.getRequests().get(2));

                List<Long> ticketIds = new ArrayList<>();

                for (TicketEntity ticket : savedTicketsBronze) {
                    ticketIds.add(ticket.getId());
                }
                for (TicketEntity ticket : savedTicketsSilver) {
                    ticketIds.add(ticket.getId());
                }
                for (TicketEntity ticket : savedTicketsGold) {
                    ticketIds.add(ticket.getId());
                }
                return CreateTicketResponse.builder()
                        .ticketId(ticketIds)
                        .build();
            }else
                throw new TicketNotFoundException();
        }else

            throw new EventNotFoundException();

    }

    private List<TicketEntity> saveNewTickets(Long eventId, CreateTicketRequest request) {

        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException(eventId.toString()));

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
