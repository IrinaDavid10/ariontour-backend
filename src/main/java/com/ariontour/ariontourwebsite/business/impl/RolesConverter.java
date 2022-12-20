package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.exception.InvalidCredentialsException;
import com.ariontour.ariontourwebsite.business.exception.InvalidLocationException;
import com.ariontour.ariontourwebsite.domain.*;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import com.ariontour.ariontourwebsite.persistance.entity.UserEntity;
import com.ariontour.ariontourwebsite.persistance.entity.UserRoleEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RolesConverter {
    public static Set<UserRole> convert(Set<UserRoleEntity> userRoleEntities) {
        Set<UserRole> userRoles = new HashSet<>();
        for (UserRoleEntity userRoleEntity : userRoleEntities) {

            UserRole userRole = UserRole.builder()
                    .id(userRoleEntity.getId())
                    .role(userRoleEntity.getRole())
                    .build();

            userRoles.add(userRole);
        }
        return userRoles;
    }


}
