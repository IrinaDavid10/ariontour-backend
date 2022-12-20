package com.ariontour.ariontourwebsite.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLiveSupportChatRequest {


    private String username;
   private String subject;
}
