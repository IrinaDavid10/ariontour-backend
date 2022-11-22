package com.ariontour.ariontourwebsite.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdminAccountRequest {


    @NotBlank
    @Length(min = 2, max = 20)
    private  String username;

    @NotBlank
    @Length(min = 2)
    private  String password;
}
