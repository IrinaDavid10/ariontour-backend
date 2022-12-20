package com.ariontour.ariontourwebsite.business.impl;


import com.ariontour.ariontourwebsite.business.GetLiveSupportChatsUseCase;
import com.ariontour.ariontourwebsite.domain.GetLiveSupportChatsResponse;
import com.ariontour.ariontourwebsite.domain.LiveSupportChat;
import com.ariontour.ariontourwebsite.persistance.LiveSupportChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetLiveSupportChatsUseCaseImpl implements GetLiveSupportChatsUseCase {

    private LiveSupportChatRepository liveSupportChatRepository;

    @Override
    public GetLiveSupportChatsResponse getChats() {
        List<LiveSupportChat> chats = liveSupportChatRepository.findAll()
                .stream()
                .map(LiveSupportChatConverter::convert)
                .toList();
        return GetLiveSupportChatsResponse.builder()
                .chats(chats)
                .build();
    }
}
