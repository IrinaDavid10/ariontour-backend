package com.ariontour.ariontourwebsite.domain;


import com.ariontour.ariontourwebsite.persistance.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    private Long id;
    private RoleEnum role;
    private User user;
}
