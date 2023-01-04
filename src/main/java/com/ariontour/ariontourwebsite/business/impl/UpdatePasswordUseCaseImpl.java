package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.UpdatePasswordUseCase;
import com.ariontour.ariontourwebsite.business.exception.InvalidCredentialsException;
import com.ariontour.ariontourwebsite.business.exception.UsernameAlreadyExistsException;
import com.ariontour.ariontourwebsite.domain.UpdatePasswordRequest;
import com.ariontour.ariontourwebsite.domain.UpdatePasswordResponse;
import com.ariontour.ariontourwebsite.persistance.UserRepository;
import com.ariontour.ariontourwebsite.persistance.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePasswordUseCaseImpl implements UpdatePasswordUseCase {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public UpdatePasswordResponse updatePassword(UpdatePasswordRequest request){
        System.out.println(request.getUsername());
        if(userRepository.existsByUsername(request.getUsername())) {
            UserEntity passwordToUpdate = userRepository.findByUsername(request.getUsername());

            if (passwordEncoder.matches(request.getOldPassword(), passwordToUpdate.getPassword())) {
                String encodedPassword = passwordEncoder.encode(request.getNewPassword());
                passwordToUpdate.setPassword(encodedPassword);
                userRepository.save(passwordToUpdate);
                return UpdatePasswordResponse.builder().userId(passwordToUpdate.getId()).build();
            }
            throw new InvalidCredentialsException();
        }

        throw new UsernameNotFoundException("Not a user");



    }
}
