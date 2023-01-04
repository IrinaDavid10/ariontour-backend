package com.ariontour.ariontourwebsite.controller;


import com.ariontour.ariontourwebsite.business.UpdatePasswordUseCase;
import com.ariontour.ariontourwebsite.domain.CreateLocationRequest;
import com.ariontour.ariontourwebsite.domain.UpdatePasswordRequest;
import com.ariontour.ariontourwebsite.domain.UpdatePasswordResponse;
import com.ariontour.ariontourwebsite.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins ={"http://localhost:3000"})
@AllArgsConstructor
public class UserController {

    private UpdatePasswordUseCase updatePasswordUseCase;

    @PutMapping("{username}")
    public ResponseEntity<UpdatePasswordResponse> updatePassword(@RequestBody UpdatePasswordRequest request){
        System.out.println(request.toString());
    UpdatePasswordResponse response = updatePasswordUseCase.updatePassword(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
