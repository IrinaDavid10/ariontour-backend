package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.RegisterCustomerAccountUseCase;
import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.business.exception.UsernameAlreadyExistsException;
import com.ariontour.ariontourwebsite.domain.CreateCustomerRequest;
import com.ariontour.ariontourwebsite.domain.CreateCustomerResponse;
import com.ariontour.ariontourwebsite.domain.RegisterCustomerAccountRequest;
import com.ariontour.ariontourwebsite.domain.RegisterCustomerAccountResponse;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.UserRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import com.ariontour.ariontourwebsite.persistance.entity.RoleEnum;
import com.ariontour.ariontourwebsite.persistance.entity.UserEntity;
import com.ariontour.ariontourwebsite.persistance.entity.UserRoleEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterCustomerAccountUseCaseImpl implements RegisterCustomerAccountUseCase {
    private final CustomerRepository customerRepository;
    private final CountryRepository countryRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public RegisterCustomerAccountResponse registerCustomer(RegisterCustomerAccountRequest request){
        if(!userRepository.existsByUsername(request.getUsername())) {
            if (countryRepository.existsByCountryCode(request.getCountry_code())) {
                CustomerEntity savedCustomer = saveNewCustomer(request);
                return RegisterCustomerAccountResponse.builder()
                        .customerId(savedCustomer.getId())
                        .build();
            }//verifica daca ce ai introdus se potriveste cu ceea ce exista in DB
            throw new InvalidCountryException("Country with code "+request.getCountry_code()+" does not exist.");
        }

        throw new UsernameAlreadyExistsException();

    }

    private CustomerEntity saveNewCustomer(RegisterCustomerAccountRequest request){
        CustomerEntity newCustomer = CustomerEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .country(countryRepository.findByCountryCode(request.getCountry_code()))
                .build();
        CustomerEntity savedCustomer = customerRepository.save(newCustomer);
        saveNewUser(request.getUsername(),request.getPassword(),newCustomer);
        return savedCustomer;
    }

    private void saveNewUser(String username, String password, CustomerEntity customer) {
        String encodedPassword = passwordEncoder.encode(password);

        UserEntity newUser = UserEntity.builder()
                .username(username)
                .password(encodedPassword)
                .customer(customer)
                .build();

        newUser.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(newUser)
                        .role(RoleEnum.CUSTOMER)
                        .build()));

        userRepository.save(newUser);
    }
}
