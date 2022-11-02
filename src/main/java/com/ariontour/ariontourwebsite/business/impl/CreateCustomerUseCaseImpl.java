package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateCustomerUseCase;
import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.domain.CreateCustomerRequest;
import com.ariontour.ariontourwebsite.domain.CreateCustomerResponse;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {
    private final CustomerRepository customerRepository;
    private final CountryRepository countryRepository;

    @Override
    @Transactional
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request){
        if(countryRepository.existsByCountryCode(request.getCountry_code())){
            CustomerEntity savedCustomer = saveNewCustomer(request);
            return CreateCustomerResponse.builder()
                    .customerId(savedCustomer.getId())
                    .build();
        }//verifica daca ce ai introdus se potriveste cu ceea ce exista in DB

        throw new InvalidCountryException("Country with code "+request.getCountry_code()+" does not exist.");

    }

    private CustomerEntity saveNewCustomer(CreateCustomerRequest request){
        CustomerEntity newCustomer = CustomerEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .country(countryRepository.findByCountryCode(request.getCountry_code()))
                .build();
        return customerRepository.save(newCustomer);
    }
}
