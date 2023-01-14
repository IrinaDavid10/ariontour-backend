package com.ariontour.ariontourwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TicketNotFoundException extends ResponseStatusException {
    public TicketNotFoundException(){super(HttpStatus.BAD_REQUEST, "TICKET NOT FOUND");}
}
