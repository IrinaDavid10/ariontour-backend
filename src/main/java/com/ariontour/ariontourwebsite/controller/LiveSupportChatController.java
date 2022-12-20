package com.ariontour.ariontourwebsite.controller;


import com.ariontour.ariontourwebsite.business.CreateLiveSupportChatUseCase;
import com.ariontour.ariontourwebsite.business.GetLiveSupportChatsUseCase;
import com.ariontour.ariontourwebsite.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
@CrossOrigin(origins ={"http://localhost:3000"})
@AllArgsConstructor
public class LiveSupportChatController {

    private final CreateLiveSupportChatUseCase createLiveSupportChatUseCase;
    private final GetLiveSupportChatsUseCase getLiveSupportChatsUseCase;

    @PostMapping
    public ResponseEntity<CreateLiveSupportChatResponse> createLiveSupportChat(@RequestBody CreateLiveSupportChatRequest request) {
        CreateLiveSupportChatResponse response = createLiveSupportChatUseCase.createLiveSupportChat(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<GetLiveSupportChatsResponse> getLiveSupportChats() {
        return ResponseEntity.ok(getLiveSupportChatsUseCase.getChats());
    }
}