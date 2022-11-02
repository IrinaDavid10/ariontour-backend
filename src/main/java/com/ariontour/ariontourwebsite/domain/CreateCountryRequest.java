package com.ariontour.ariontourwebsite.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCountryRequest {
    @NotBlank
    @Length (min = 2, max = 50)
    @Column(name = "country_name")
    private String country_name;

    @NotBlank
    @Length (min = 2, max = 2)
    @Column (name = "country_code")
    private String country_code;


}