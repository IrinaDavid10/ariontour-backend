package com.ariontour.ariontourwebsite.repository;

import com.ariontour.ariontourwebsite.persistance.CountryRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CountryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =
        AutoConfigureTestDatabase.Replace.NONE)
public class CountryRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CountryRepository countryRepository;

    @Test
    void save_shouldSaveCountryWithAllFields(){
        CountryEntity country = CountryEntity.builder()
                .countryName("Romania")
                .countryCode("UQ")
                .build();
        CountryEntity savedCountry = countryRepository.save(country);
        assertNotNull(savedCountry.getId());
        savedCountry = entityManager.find(CountryEntity.class, savedCountry.getId());
        CountryEntity expectedCountry = CountryEntity.builder()
                .id(savedCountry.getId())
                .countryName("Romania")
                .countryCode("UQ")
                .build();
        assertEquals(expectedCountry, savedCountry);
    }
}
