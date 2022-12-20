package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.CreateEventUseCase;
import com.ariontour.ariontourwebsite.business.GetEventTicketsAmountByTypeUseCase;
import com.ariontour.ariontourwebsite.business.GetEventUseCase;
import com.ariontour.ariontourwebsite.business.GetEventsUseCase;
import com.ariontour.ariontourwebsite.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins ={"http://localhost:3000"})
@AllArgsConstructor
public class EventsController {
    private final CreateEventUseCase createEventUseCase;
    private final GetEventsUseCase getEventsUseCase;
    private final GetEventUseCase getEventUseCase;
    private final GetEventTicketsAmountByTypeUseCase getEventTicketsAmountByTypeUseCase;
    @GetMapping
    public ResponseEntity<GetEventsResponse> getEvents(){
        return ResponseEntity.ok(getEventsUseCase.getEvents());
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Event> getEvent(@PathVariable(value = "id") final long id){
        final Optional<Event> eventOptional = getEventUseCase.getEvent(id);
        if(eventOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(eventOptional.get());
    }

    @GetMapping("/{eventId}/tickets")
    public ResponseEntity<GetEventTicketsAmountByTypeResponse> getTicketsAmountByType(@PathVariable(value = "eventId")  final Long eventId,@RequestParam(value = "type_id") final Long type_id){

        return ResponseEntity.ok().body(getEventTicketsAmountByTypeUseCase.getTicketsAmountByType(type_id, eventId));
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<CreateEventResponse> createEvent(@RequestBody CreateEventRequest request, @RequestParam("localDateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime){
        System.out.println(localDateTime);
        CreateEventResponse response = createEventUseCase.createEvent(request,localDateTime);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
