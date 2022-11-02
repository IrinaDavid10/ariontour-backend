package com.ariontour.ariontourwebsite.repository;

import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =
        AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CountryRepository countryRepository;

    @Test
    void save_ShouldSaveCustomerWithAllFields(){
        CountryEntity netherlands = saveCountry("Netherlands", "NL");
        CustomerEntity customer = CustomerEntity.builder()
                .firstName("Maria")
                .lastName("Lebron")
                .country(netherlands)
                .build();
        CustomerEntity savedCustomer = customerRepository.save(customer);
        assertNotNull(savedCustomer.getId());
        savedCustomer = entityManager.find(CustomerEntity.class, savedCustomer.getId());
        CustomerEntity expectedCustomer = CustomerEntity.builder()
                .id(savedCustomer.getId())
                .firstName("Maria")
                .lastName("Lebron")
                .country(netherlands)
                .build();
        assertEquals(expectedCustomer, savedCustomer);
    }

    private CountryEntity saveCountry(String name, String code){
        if(countryRepository.existsByCountryCode(code)){
            return countryRepository.findByCountryCode(code);
        }
                CountryEntity country = CountryEntity.builder()
                .countryName(name)
                .countryCode(code)
                .build();
        entityManager.persist(country);
        return country;
    }
}
