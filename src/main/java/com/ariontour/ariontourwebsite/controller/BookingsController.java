package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.CreateBookingUseCase;
import com.ariontour.ariontourwebsite.domain.CreateBookingRequest;
import com.ariontour.ariontourwebsite.domain.CreateBookingResponse;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins ={"http://localhost:3000"})
@AllArgsConstructor
public class BookingsController {
    private final CreateBookingUseCase createBookingUseCase;

    @PostMapping
    public ResponseEntity<CreateBookingResponse> createBooking(@RequestBody CreateBookingRequest request){
        CreateBookingResponse response = createBookingUseCase.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
