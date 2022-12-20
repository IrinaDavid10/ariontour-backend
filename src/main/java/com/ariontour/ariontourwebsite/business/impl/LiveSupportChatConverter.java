package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.exception.InvalidCredentialsException;
import com.ariontour.ariontourwebsite.domain.LiveSupportChat;
import com.ariontour.ariontourwebsite.domain.User;
import com.ariontour.ariontourwebsite.persistance.entity.LiveSupportChatEntity;

import java.util.Optional;

public class LiveSupportChatConverter {


    public static LiveSupportChat convert(LiveSupportChatEntity liveSupportChatEntity) {
        User user = Optional.ofNullable(liveSupportChatEntity.getUser())
                .map(UserConverter::convert)
                .orElseThrow(() -> new InvalidCredentialsException());
        User admin = Optional.ofNullable(liveSupportChatEntity.getAdmin())
                .map(UserConverter::convert)
                .orElse(null);
        if (admin != null)
            return LiveSupportChat.builder()
                    .id(liveSupportChatEntity.getId())
                    .subject(liveSupportChatEntity.getSubject())
                    .user(user)
                    .admin(admin)
                    .build();

        return LiveSupportChat.builder()
                .id(liveSupportChatEntity.getId())
                .subject(liveSupportChatEntity.getSubject())
                .user(user)
                .build();
    }


}
