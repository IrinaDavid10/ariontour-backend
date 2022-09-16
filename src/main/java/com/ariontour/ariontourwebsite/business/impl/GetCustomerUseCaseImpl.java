package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.GetCustomerUseCase;
import com.ariontour.ariontourwebsite.domain.Customer;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class GetCustomerUseCaseImpl implements GetCustomerUseCase {
    private CustomerRepository customerRepository;

    @Override
    public Optional<Customer> getCustomer(long customerId){
        return customerRepository.findById(customerId).map(CustomerConverter::convert);
    }

}
