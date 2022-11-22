package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.LoginUseCase;
import com.ariontour.ariontourwebsite.domain.LoginRequest;
import com.ariontour.ariontourwebsite.domain.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
@CrossOrigin(origins ={"http://localhost:3000"})

public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
