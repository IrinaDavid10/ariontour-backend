package com.ariontour.ariontourwebsite.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiveSupportChat {

    private Long id;
    private User user;
    private User admin;
    private String subject;

}
