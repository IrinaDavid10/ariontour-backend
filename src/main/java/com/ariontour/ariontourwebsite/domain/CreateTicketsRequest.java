package com.ariontour.ariontourwebsite.domain;

import com.ariontour.ariontourwebsite.persistance.entity.TicketEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketsRequest {
    private Long eventId;
    private List<CreateTicketRequest> requests;
}
