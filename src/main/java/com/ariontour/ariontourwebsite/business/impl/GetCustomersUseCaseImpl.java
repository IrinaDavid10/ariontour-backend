package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.GetCustomersUseCase;
import com.ariontour.ariontourwebsite.domain.Customer;
import com.ariontour.ariontourwebsite.domain.GetCustomersResponse;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class GetCustomersUseCaseImpl implements GetCustomersUseCase{
    private CustomerRepository customerRepository;

    @Override
    public GetCustomersResponse getCustomers(){
        List<Customer> customers = customerRepository.findAll()
                .stream()
                .map(CustomerConverter::convert)
                .toList();

        return GetCustomersResponse.builder()
                .customers(customers)
                .build();
    }
}
