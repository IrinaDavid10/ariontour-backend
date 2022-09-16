package com.ariontour.ariontourwebsite.persistance.entity;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CustomerEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private String username;
}
