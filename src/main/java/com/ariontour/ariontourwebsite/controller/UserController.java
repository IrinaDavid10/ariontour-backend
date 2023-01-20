package com.ariontour.ariontourwebsite.controller;


import com.ariontour.ariontourwebsite.business.DeleteUserUseCase;
import com.ariontour.ariontourwebsite.business.GetAllUsersUseCase;
import com.ariontour.ariontourwebsite.business.UpdatePasswordUseCase;
import com.ariontour.ariontourwebsite.domain.DeleteUserRequest;
import com.ariontour.ariontourwebsite.domain.GetAllUsersResponse;
import com.ariontour.ariontourwebsite.domain.UpdatePasswordRequest;
import com.ariontour.ariontourwebsite.domain.UpdatePasswordResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:3000"})
@AllArgsConstructor
public class UserController {

    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    @PutMapping("{username}")
    public ResponseEntity<UpdatePasswordResponse> updatePassword(@RequestBody UpdatePasswordRequest request) {
        System.out.println(request.toString());
        UpdatePasswordResponse response = updatePasswordUseCase.updatePassword(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getUsers() {
        return ResponseEntity.ok(getAllUsersUseCase.getUsers());
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable(value = "id") Long userId) {
        DeleteUserRequest request = new DeleteUserRequest(userId);
        System.out.println(userId);
        if (deleteUserUseCase.deleteUser(userId)) {
            return ResponseEntity.ok(userId);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userId);
    }

}
