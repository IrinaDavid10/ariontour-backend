package com.ariontour.ariontourwebsite.domain;

import com.ariontour.ariontourwebsite.persistance.entity.EventEntity;
import com.ariontour.ariontourwebsite.persistance.entity.TicketEnum;
import com.ariontour.ariontourwebsite.persistance.entity.TicketTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketRequest {
    private Long eventId;
    private TicketEnum ticketType;
    private Double price;
}
