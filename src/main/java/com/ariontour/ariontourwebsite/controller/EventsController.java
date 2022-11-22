package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.CreateEventUseCase;
import com.ariontour.ariontourwebsite.domain.CreateEventRequest;
import com.ariontour.ariontourwebsite.domain.CreateEventResponse;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins ={"http://localhost:3000"})
@AllArgsConstructor
public class EventsController {
    private final CreateEventUseCase createEventUseCase;

    @PostMapping
    public ResponseEntity<CreateEventResponse> createEvent(@RequestBody CreateEventRequest request, @RequestParam("localDateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime){
        System.out.println(localDateTime);
        CreateEventResponse response = createEventUseCase.createEvent(request,localDateTime);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
