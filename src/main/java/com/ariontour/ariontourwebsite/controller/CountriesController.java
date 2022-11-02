package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.CreateCountryUseCase;
import com.ariontour.ariontourwebsite.business.GetCountriesUseCase;
import com.ariontour.ariontourwebsite.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
@CrossOrigin(origins ={"http://localhost:3000"})
@AllArgsConstructor

public class CountriesController {
    private final CreateCountryUseCase createCountryUseCase;
    private final GetCountriesUseCase getCountriesUseCase;

    @PostMapping
    public ResponseEntity<CreateCountryResponse> createCountry(@RequestBody CreateCountryRequest request){
        CreateCountryResponse response = createCountryUseCase.createCountry(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<GetCountryResponse> getCustomers(){
        return ResponseEntity.ok(getCountriesUseCase.getCountries());
    }
}