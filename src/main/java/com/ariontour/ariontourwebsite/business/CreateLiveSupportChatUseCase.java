package com.ariontour.ariontourwebsite.business;


import com.ariontour.ariontourwebsite.domain.CreateLiveSupportChatRequest;
import com.ariontour.ariontourwebsite.domain.CreateLiveSupportChatResponse;

public interface CreateLiveSupportChatUseCase {
    CreateLiveSupportChatResponse createLiveSupportChat(CreateLiveSupportChatRequest request);
}
