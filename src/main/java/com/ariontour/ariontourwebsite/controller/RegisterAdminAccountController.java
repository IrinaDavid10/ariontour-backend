package com.ariontour.ariontourwebsite.controller;


import com.ariontour.ariontourwebsite.business.RegisterAdminAccountUseCase;
import com.ariontour.ariontourwebsite.domain.RegisterAdminAccountRequest;
import com.ariontour.ariontourwebsite.domain.RegisterAdminAccountResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registeradmin")
@CrossOrigin(origins ={"http://localhost:3000"})
@AllArgsConstructor
public class RegisterAdminAccountController {
    private final RegisterAdminAccountUseCase registerAdminAccountUseCase;

    @PostMapping
    public ResponseEntity<RegisterAdminAccountResponse> registerAdminAccount(@RequestBody RegisterAdminAccountRequest request){
        RegisterAdminAccountResponse response = registerAdminAccountUseCase.registerAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }}
