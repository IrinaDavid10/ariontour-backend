package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.*;
import com.ariontour.ariontourwebsite.persistance.entity.BookingEntity;
import com.ariontour.ariontourwebsite.persistance.entity.TicketEntity;

import java.util.Optional;

public class BookingConverter {
    private BookingConverter() {
    }

    public static Booking convert(BookingEntity bookingEntity) {
        Customer customer = Optional.ofNullable(bookingEntity.getCustomer())
                .map(CustomerConverter::convert)
                .orElse(null);
        Ticket ticket = Optional.ofNullable(bookingEntity.getTicket())
                .map(TicketConverter::convert)
                .orElse(null);

        return Booking.builder()
                .id(bookingEntity.getId())
                .customer(customer)
                .dateTime(bookingEntity.getDateTime())
                .ticket(ticket)
                .build();
    }
}


