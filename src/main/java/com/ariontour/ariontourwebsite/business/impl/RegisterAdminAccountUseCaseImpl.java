package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.RegisterAdminAccountUseCase;
import com.ariontour.ariontourwebsite.business.exception.UsernameAlreadyExistsException;
import com.ariontour.ariontourwebsite.domain.*;
import com.ariontour.ariontourwebsite.persistance.UserRepository;
import com.ariontour.ariontourwebsite.persistance.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterAdminAccountUseCaseImpl implements RegisterAdminAccountUseCase {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public RegisterAdminAccountResponse registerAdmin(RegisterAdminAccountRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }
        UserEntity savedAdmin = saveNewUser(request.getUsername(),request.getPassword());
        return RegisterAdminAccountResponse.builder()
                .adminId(savedAdmin.getId())
                .build();

    }



    private UserEntity saveNewUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        UserEntity newUser = UserEntity.builder()
                .username(username)
                .password(encodedPassword)
                .build();

        newUser.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(newUser)
                        .role(RoleEnum.ADMIN)
                        .build()));


        return userRepository.save(newUser);
    }
}