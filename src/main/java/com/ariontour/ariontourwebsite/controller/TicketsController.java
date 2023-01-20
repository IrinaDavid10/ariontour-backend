package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.CreateTicketUseCase;
import com.ariontour.ariontourwebsite.business.GetBookedTicketsByEventUseCase;
import com.ariontour.ariontourwebsite.business.GetTicketsUseCase;
import com.ariontour.ariontourwebsite.domain.*;
import com.ariontour.ariontourwebsite.persistance.entity.TicketEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = {"http://localhost:3000"})
@AllArgsConstructor
public class TicketsController {
    private final CreateTicketUseCase createTicketUseCase;
    private final GetTicketsUseCase getTicketsUseCase;
    private final GetBookedTicketsByEventUseCase getBookedTicketsByEventUseCase;

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<CreateTicketResponse> createTicket(@RequestBody CreateTicketsRequest request) {
        CreateTicketResponse response = createTicketUseCase.createTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @GetMapping
    public ResponseEntity<GetAllAvailableTicketsResponse> getAllUnbookedTickets(@RequestParam(value = "eventId", required = true) String eventId, @RequestParam(value = "ticketType", required = true) String ticketType) {
        GetAllAvailableTicketsRequest request = new GetAllAvailableTicketsRequest(Long.parseLong(eventId), TicketEnum.values()[Integer.parseInt(ticketType)]);
        GetAllAvailableTicketsResponse response = getTicketsUseCase.getNumberOfAvailableTickets(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bookedTickets")
    public ResponseEntity<List<Ticket>> getBookedTicketsByEvent(@RequestParam(value = "eventId", required = true) Long eventId){
        List<Ticket> response = getBookedTicketsByEventUseCase.getBookedTicketsByEvent(eventId);
        return ResponseEntity.ok(response);
    }


}
