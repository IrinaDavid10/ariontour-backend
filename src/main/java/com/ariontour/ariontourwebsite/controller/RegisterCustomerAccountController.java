package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.RegisterCustomerAccountUseCase;
import com.ariontour.ariontourwebsite.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registercustomer")
@CrossOrigin(origins ={"http://localhost:3000"})
@AllArgsConstructor
public class RegisterCustomerAccountController {
    private final RegisterCustomerAccountUseCase registerCustomerAccountUseCase;

    @PostMapping
    public ResponseEntity<RegisterCustomerAccountResponse> registerCustomerAccount(@RequestBody RegisterCustomerAccountRequest request){
        RegisterCustomerAccountResponse response = registerCustomerAccountUseCase.registerCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }}
