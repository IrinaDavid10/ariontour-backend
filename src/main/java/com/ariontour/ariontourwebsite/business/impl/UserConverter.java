package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.business.exception.InvalidCredentialsException;
import com.ariontour.ariontourwebsite.business.exception.InvalidLocationException;
import com.ariontour.ariontourwebsite.domain.*;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import com.ariontour.ariontourwebsite.persistance.entity.UserEntity;

import java.util.Optional;
import java.util.Set;

public class UserConverter {
    public static User convert(UserEntity userEntity)
    {
        Set<UserRole> userRoles = Optional.ofNullable(userEntity.getUserRoles())
                .map(RolesConverter::convert)
                .orElseThrow(() -> new InvalidCredentialsException());


        if(userEntity.getCustomer()!=null) {
            CustomerEntity customerEntity = userEntity.getCustomer();
            Country country = Optional.ofNullable(customerEntity.getCountry())
                    .map(CountryConverter::convert)
                    .orElseThrow(() -> new InvalidCredentialsException());
            Customer customer = Customer.builder()
                    .id(customerEntity.getId())
                    .firstName(customerEntity.getFirstName())
                    .lastName(customerEntity.getLastName())
                    .country(country)
                    .build();
            return User.builder()
                    .id(userEntity.getId())
                    .customer(customer)
                    .username(userEntity.getUsername())
                    .roles(userRoles)
                    .build();
        }

        return User.builder()
                .id(userEntity.getId())

                .username(userEntity.getUsername())
                .roles(userRoles)
                .build();
    }
}
