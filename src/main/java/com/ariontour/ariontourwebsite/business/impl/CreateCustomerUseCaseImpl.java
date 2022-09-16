package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateCustomerUseCase;
import com.ariontour.ariontourwebsite.business.exception.UsernameAlreadyExistsException;
import com.ariontour.ariontourwebsite.domain.CreateCustomerRequest;
import com.ariontour.ariontourwebsite.domain.CreateCustomerResponse;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {
    private final CustomerRepository customerRepository;

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request){
        if(customerRepository.existsByUsername(request.getUsername())){
            throw new UsernameAlreadyExistsException();
        }
        CustomerEntity savedCustomer = saveNewCustomer(request);
        return CreateCustomerResponse.builder()
                .customerId(savedCustomer.getId())
                .build();
    }

    private CustomerEntity saveNewCustomer(CreateCustomerRequest request){
        CustomerEntity newCustomer = CustomerEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .country(request.getCountry())
                .username(request.getUsername())
                .build();
        return customerRepository.save(newCustomer);
    }
}
