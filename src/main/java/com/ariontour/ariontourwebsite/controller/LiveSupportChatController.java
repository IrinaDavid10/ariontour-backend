package com.ariontour.ariontourwebsite.controller;


import com.ariontour.ariontourwebsite.business.CreateLiveSupportChatUseCase;
import com.ariontour.ariontourwebsite.business.GetLiveSupportChatsUseCase;
import com.ariontour.ariontourwebsite.domain.CreateLiveSupportChatRequest;
import com.ariontour.ariontourwebsite.domain.CreateLiveSupportChatResponse;
import com.ariontour.ariontourwebsite.domain.GetLiveSupportChatsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/chats")
@CrossOrigin(origins = {"http://localhost:3000"})
@AllArgsConstructor
public class LiveSupportChatController {

    private final CreateLiveSupportChatUseCase createLiveSupportChatUseCase;
    private final GetLiveSupportChatsUseCase getLiveSupportChatsUseCase;

    @RolesAllowed({"ROLE_CUSTOMER"})
    @PostMapping
    public ResponseEntity<CreateLiveSupportChatResponse> createLiveSupportChat(@RequestBody CreateLiveSupportChatRequest request) {
        CreateLiveSupportChatResponse response = createLiveSupportChatUseCase.createLiveSupportChat(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<GetLiveSupportChatsResponse> getLiveSupportChats() {
        return ResponseEntity.ok(getLiveSupportChatsUseCase.getChats());
    }
}