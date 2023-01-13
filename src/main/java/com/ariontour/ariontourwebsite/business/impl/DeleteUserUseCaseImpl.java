package com.ariontour.ariontourwebsite.business.impl;


import com.ariontour.ariontourwebsite.business.DeleteCustomerUseCase;
import com.ariontour.ariontourwebsite.business.DeleteUserUseCase;
import com.ariontour.ariontourwebsite.domain.DeleteCustomerRequest;
import com.ariontour.ariontourwebsite.domain.DeleteUserRequest;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    @Override
    public boolean deleteUser(Long userId) {
        if(userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
                return true;

        }
        return false;
    }
}
