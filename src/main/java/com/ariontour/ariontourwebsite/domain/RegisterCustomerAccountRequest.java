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
public class RegisterCustomerAccountRequest {
    @NotBlank
    @Length(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Length(min = 2, max = 2)
    private String country_code;

    @NotBlank
    @Length(min = 2, max = 20)
    private  String username;

    @NotBlank
    @Length(min = 2)
    private  String password;
}
