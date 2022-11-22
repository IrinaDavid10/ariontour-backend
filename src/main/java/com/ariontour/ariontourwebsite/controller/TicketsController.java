package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.CreateBookingUseCase;
import com.ariontour.ariontourwebsite.business.CreateTicketUseCase;
import com.ariontour.ariontourwebsite.domain.CreateBookingRequest;
import com.ariontour.ariontourwebsite.domain.CreateBookingResponse;
import com.ariontour.ariontourwebsite.domain.CreateTicketRequest;
import com.ariontour.ariontourwebsite.domain.CreateTicketResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins ={"http://localhost:3000"})
@AllArgsConstructor
public class TicketsController {
    private final CreateTicketUseCase createTicketUseCase;

    @PostMapping
    public ResponseEntity<CreateTicketResponse> createTicket(@RequestBody CreateTicketRequest request){
        CreateTicketResponse response = createTicketUseCase.createTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
