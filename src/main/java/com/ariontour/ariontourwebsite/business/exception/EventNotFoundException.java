package com.ariontour.ariontourwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EventNotFoundException extends ResponseStatusException {
    public EventNotFoundException(){super(HttpStatus.BAD_REQUEST, "EVENT DOES NOT EXIST");}
}
