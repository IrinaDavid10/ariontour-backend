package com.ariontour.ariontourwebsite.configuration.db;

import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer{
    private CustomerRepository customerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData(){
        if(customerRepository.count()==0){
            customerRepository.save(CustomerEntity.builder().firstName("Sally").lastName("Jenner").country("USA").username("sallyj").build());
            customerRepository.save(CustomerEntity.builder().firstName("Maria").lastName("Lebron").country("Spain").username("mlebron").build());
            customerRepository.save(CustomerEntity.builder().firstName("Samantha").lastName("Jordy").country("Italy").username("samjordy").build());
            customerRepository.save(CustomerEntity.builder().firstName("Kendra").lastName("Watson").country("UK").username("kwatson").build());
            customerRepository.save(CustomerEntity.builder().firstName("Jerome").lastName("Brown").country("Denmark").username("jeromeb").build());

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