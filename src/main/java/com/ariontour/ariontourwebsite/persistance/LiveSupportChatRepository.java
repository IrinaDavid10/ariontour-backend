package com.ariontour.ariontourwebsite.persistance;

import com.ariontour.ariontourwebsite.persistance.entity.LiveSupportChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveSupportChatRepository extends JpaRepository<LiveSupportChatEntity, Long> {
}
