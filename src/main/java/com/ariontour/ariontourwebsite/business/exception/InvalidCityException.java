package com.ariontour.ariontourwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCityException extends ResponseStatusException {
    /*
    public InvalidCountryException() {
        super(HttpStatus.BAD_REQUEST, "LOCATION_INVALID");
    }

     */

    public InvalidCityException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
