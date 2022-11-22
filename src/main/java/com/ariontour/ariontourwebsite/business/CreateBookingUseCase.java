package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.CreateBookingRequest;
import com.ariontour.ariontourwebsite.domain.CreateBookingResponse;

import java.time.LocalDateTime;


public interface CreateBookingUseCase {
    CreateBookingResponse createBooking(CreateBookingRequest request);
}
