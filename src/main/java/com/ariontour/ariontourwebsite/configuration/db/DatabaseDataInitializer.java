package com.ariontour.ariontourwebsite.configuration.db;

import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer{
    private CustomerRepository customerRepository;
    private CountryRepository countryRepository;
    //Used once to populate DB
    //@EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData(){
        CountryEntity romania = countryRepository.save(CountryEntity.builder().countryCode("RO").countryName("Romania").build());
        CountryEntity netherlands = countryRepository.save(CountryEntity.builder().countryCode("NL").countryName("Netherlands").build());
        if(customerRepository.count()==0){
            customerRepository.save(CustomerEntity.builder().firstName("Sally").lastName("Jenner").country(romania).build());
            customerRepository.save(CustomerEntity.builder().firstName("Maria").lastName("Lebron").country(netherlands).build());
            customerRepository.save(CustomerEntity.builder().firstName("Samantha").lastName("Jordy").country(romania).build());
            customerRepository.save(CustomerEntity.builder().firstName("Kendra").lastName("Watson").country(romania).build());
            customerRepository.save(CustomerEntity.builder().firstName("Jerome").lastName("Brown").country(netherlands).build());

        }
    }
}
/*
private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private String username;
 */