package com.ariontour.ariontourwebsite.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class CreateTicketResponse {
    private List<Long> ticketId;
}
