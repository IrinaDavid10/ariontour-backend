package com.ariontour.ariontourwebsite.persistance;

import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    List<CustomerEntity> findAll();
    CustomerEntity save(CustomerEntity customer);
    void deleteById(long customerId);
    Optional<CustomerEntity> findById(long customerId);
    boolean existsById(long customerId);

    int count();

    boolean existsByUsername(String username);
}
