package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.AccessTokenDecoder;
import com.ariontour.ariontourwebsite.business.CreateBookingUseCase;
import com.ariontour.ariontourwebsite.business.GetBookedEventsUseCase;
import com.ariontour.ariontourwebsite.business.GetBookedTicketsUseCase;
import com.ariontour.ariontourwebsite.domain.CreateBookingRequest;
import com.ariontour.ariontourwebsite.domain.CreateBookingResponse;
import com.ariontour.ariontourwebsite.domain.GetBookedEventsResponse;
import com.ariontour.ariontourwebsite.domain.GetBookedTicketsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = {"http://localhost:3000"})
@AllArgsConstructor
public class BookingsController {
    private final CreateBookingUseCase createBookingUseCase;
    private final GetBookedTicketsUseCase getBookedTicketsUseCase;
    private final GetBookedEventsUseCase getBookedEventsUseCase;
    private final AccessTokenDecoder accessTokenDecoder;


    @PostMapping
    @RolesAllowed({"ROLE_CUSTOMER"})
    public ResponseEntity<CreateBookingResponse> createBooking(@RequestBody CreateBookingRequest request) {
        CreateBookingResponse response = createBookingUseCase.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/tickets")
    @RolesAllowed({"ROLE_CUSTOMER"})
    public ResponseEntity<GetBookedTicketsResponse> getBookedTickets(@RequestParam(value = "eventId", required = true) Long eventId, @RequestParam(value = "token", required = true) String token) {
        Long customerId = accessTokenDecoder.decode(token).getCustomerId();
        return ResponseEntity.ok(getBookedTicketsUseCase.getBookedTickets(eventId, customerId));
    }

    @GetMapping("/events")
    @RolesAllowed({"ROLE_CUSTOMER"})
    public ResponseEntity<GetBookedEventsResponse> getBookedEvents(@RequestParam(value = "token", required = true) String token) {
        Long customerId = accessTokenDecoder.decode(token).getCustomerId();
        return ResponseEntity.ok(getBookedEventsUseCase.getBookedEvents(customerId));
    }
    //getMapping pt tickets prin JWT token verificam user-ul si raspundem cu ticketele userului dupa jwt token
}
