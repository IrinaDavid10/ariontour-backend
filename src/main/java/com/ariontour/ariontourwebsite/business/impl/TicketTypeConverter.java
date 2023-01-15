package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.Event;
import com.ariontour.ariontourwebsite.domain.Ticket;
import com.ariontour.ariontourwebsite.domain.TicketType;
import com.ariontour.ariontourwebsite.persistance.entity.TicketEntity;
import com.ariontour.ariontourwebsite.persistance.entity.TicketTypeEntity;

import java.util.Optional;

public class TicketTypeConverter {
    private TicketTypeConverter() {
    }

    public static TicketType convert(TicketTypeEntity ticketTypeEntity) {
        return TicketType.builder()
                .id(ticketTypeEntity.getId())
                .ticketType(ticketTypeEntity.getTicketType())
                .build();
    }
}


