package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.domain.*;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import com.ariontour.ariontourwebsite.persistance.entity.TicketEntity;

import java.util.Optional;
public class TicketConverter {
    private TicketConverter() {
    }

    public static Ticket convert(TicketEntity ticketEntity) {
        Event event = Optional.ofNullable(ticketEntity.getEvent())
                .map(EventConverter::convert)
                .orElse(null);
        TicketType ticketType = Optional.ofNullable(ticketEntity.getTicketType())
                .map(TicketTypeConverter::convert)
                .orElse(null);

        return Ticket.builder()
                .id(ticketEntity.getId())
                .event(event)
                .ticketType(ticketType)
                .price(ticketEntity.getPrice())
                .build();
    }
}

