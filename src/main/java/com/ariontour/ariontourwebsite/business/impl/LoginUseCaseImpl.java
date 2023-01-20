package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.AccessTokenEncoder;
import com.ariontour.ariontourwebsite.business.LoginUseCase;
import com.ariontour.ariontourwebsite.business.exception.InvalidCredentialsException;
import com.ariontour.ariontourwebsite.domain.AccessToken;
import com.ariontour.ariontourwebsite.domain.LoginRequest;
import com.ariontour.ariontourwebsite.domain.LoginResponse;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.UserRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import com.ariontour.ariontourwebsite.persistance.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {

        return passwordEncoder.matches(rawPassword, encodedPassword);

    }

    private String generateAccessToken(UserEntity user) {
        Long customerId = null;
        if (user.getCustomer() != null) {
            CustomerEntity customerEntity = customerRepository.findById(user.getCustomer().getId()).orElseThrow(InvalidCredentialsException::new);
            customerId = user.getCustomer().getId();

        }
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();


        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .customerId(customerId)
                        .build());
    }

}
