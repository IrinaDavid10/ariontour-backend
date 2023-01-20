package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.CreateCityUseCase;
import com.ariontour.ariontourwebsite.business.GetCitiesUseCase;
import com.ariontour.ariontourwebsite.domain.CreateCityRequest;
import com.ariontour.ariontourwebsite.domain.CreateCityResponse;
import com.ariontour.ariontourwebsite.domain.GetCitiesResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/cities")
@CrossOrigin(origins = {"http://localhost:3000"})
@AllArgsConstructor
public class CityController {
    private final CreateCityUseCase createCityUseCase;
    private final GetCitiesUseCase getCitiesUseCase;

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<CreateCityResponse> createCity(@RequestBody CreateCityRequest request) {
        CreateCityResponse response = createCityUseCase.createCity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    @GetMapping
    public ResponseEntity<GetCitiesResponse> getCities() {
        return ResponseEntity.ok(getCitiesUseCase.getCities());
    }
}
