package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.DeleteCustomerUseCase;
import com.ariontour.ariontourwebsite.domain.DeleteCustomerRequest;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {
    private final CustomerRepository customerRepository;


    @Override
    public boolean deleteCustomer(DeleteCustomerRequest request) {
        if(customerRepository.existsById(request.getId())){
            customerRepository.deleteById(request.getId());
            return true;
        }
        return false;
    }
}
