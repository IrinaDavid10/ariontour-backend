package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.CreateLocationUseCase;
import com.ariontour.ariontourwebsite.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@CrossOrigin(origins ={"http://localhost:3000"})
@AllArgsConstructor
public class LocationsController {
    private final CreateLocationUseCase createLocationUseCase;

    @PostMapping
    public ResponseEntity<CreateLocationResponse>createLocation(@RequestBody CreateLocationRequest request){
        CreateLocationResponse response = createLocationUseCase.createLocation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
