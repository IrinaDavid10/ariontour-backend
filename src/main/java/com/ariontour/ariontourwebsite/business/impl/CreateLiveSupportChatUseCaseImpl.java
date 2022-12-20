package com.ariontour.ariontourwebsite.business.impl;


import com.ariontour.ariontourwebsite.business.CreateLiveSupportChatUseCase;
import com.ariontour.ariontourwebsite.business.exception.InvalidCredentialsException;
import com.ariontour.ariontourwebsite.domain.CreateLiveSupportChatRequest;
import com.ariontour.ariontourwebsite.domain.CreateLiveSupportChatResponse;
import com.ariontour.ariontourwebsite.persistance.LiveSupportChatRepository;
import com.ariontour.ariontourwebsite.persistance.UserRepository;
import com.ariontour.ariontourwebsite.persistance.entity.LiveSupportChatEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CreateLiveSupportChatUseCaseImpl implements CreateLiveSupportChatUseCase {

    private final UserRepository userRepository;
    private final LiveSupportChatRepository liveSupportChatRepository;

    @Override
    @Transactional
    public CreateLiveSupportChatResponse createLiveSupportChat(CreateLiveSupportChatRequest request) {
        if (!userRepository.existsByUsername(request.getUsername()))
            throw new InvalidCredentialsException();

        LiveSupportChatEntity savedLiveSupportChat = saveNewLiveSupportChat(request);
        return CreateLiveSupportChatResponse.builder()
                .liveSupportChatId(savedLiveSupportChat.getId())
                .build();
    }

    private LiveSupportChatEntity saveNewLiveSupportChat(CreateLiveSupportChatRequest request) {
        if (!userRepository.existsByUsername(request.getUsername()))
            throw new InvalidCredentialsException();


        LiveSupportChatEntity newLiveSupportChat = LiveSupportChatEntity.builder()
                .subject(request.getSubject())
                .user(userRepository.findByUsername(request.getUsername()))
                .build();


        return liveSupportChatRepository.save(newLiveSupportChat);
    }
}

