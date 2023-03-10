package com.ariontour.ariontourwebsite.domain;

import com.ariontour.ariontourwebsite.persistance.entity.TicketEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {
    private Long customerId;
    private Long eventId;
    private TicketEnum ticketType;
    private Long numberOfBookings;
}
