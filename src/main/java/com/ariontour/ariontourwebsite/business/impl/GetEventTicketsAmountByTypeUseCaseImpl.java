package com.ariontour.ariontourwebsite.business.impl;


import com.ariontour.ariontourwebsite.business.GetEventTicketsAmountByTypeUseCase;
import com.ariontour.ariontourwebsite.business.GetEventsUseCase;
import com.ariontour.ariontourwebsite.domain.GetEventTicketsAmountByTypeResponse;
import com.ariontour.ariontourwebsite.persistance.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class GetEventTicketsAmountByTypeUseCaseImpl implements GetEventTicketsAmountByTypeUseCase {
    private final TicketRepository ticketRepository;
    @Override
    @Transactional
    public GetEventTicketsAmountByTypeResponse getTicketsAmountByType(Long typeId, Long eventId){
        Long total = ticketRepository.AmountOfTicketsByType(typeId,eventId);
        return GetEventTicketsAmountByTypeResponse.builder().numberOfTickets(total).build();
    }
}
