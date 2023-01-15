package com.ariontour.ariontourwebsite.domain;

import com.ariontour.ariontourwebsite.persistance.entity.TicketEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketType {
    private Long id;
    private TicketEnum ticketType;
}
