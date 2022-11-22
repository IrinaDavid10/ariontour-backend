package com.ariontour.ariontourwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsernameNotFoundException extends ResponseStatusException {
    public UsernameNotFoundException(){super(HttpStatus.BAD_REQUEST, "USERNAME NOT FOUND");}
}
