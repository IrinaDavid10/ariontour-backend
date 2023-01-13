package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.GetAllUsersUseCase;
import com.ariontour.ariontourwebsite.domain.Customer;
import com.ariontour.ariontourwebsite.domain.GetAllUsersResponse;
import com.ariontour.ariontourwebsite.domain.GetCustomersResponse;
import com.ariontour.ariontourwebsite.domain.User;
import com.ariontour.ariontourwebsite.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase {
    private UserRepository userRepository;

    @Override
    public GetAllUsersResponse getUsers(){
        List<User> users = userRepository.findAll()
                .stream()
                .map(UserConverter::convert)
                .toList();

        return GetAllUsersResponse.builder()
                .users(users)
                .build();
    }
}
