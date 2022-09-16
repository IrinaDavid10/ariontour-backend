package com.ariontour.ariontourwebsite.persistance.impl;

import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeCustomerRepositoryImpl implements CustomerRepository {
    private static long NEXT_ID = 1;

    private final List<CustomerEntity> savedCustomers;

    public FakeCustomerRepositoryImpl(){this.savedCustomers = new ArrayList<>();}

    @Override
    public List<CustomerEntity> findAll(){return Collections.unmodifiableList(this.savedCustomers);}

    @Override
    public Optional<CustomerEntity> findById(long customerId){
        return this.savedCustomers.stream()
                .filter(customerEntity -> customerEntity.getId().equals(customerId))
                .findFirst();
    }

    @Override
    public void deleteById(long customerId){
        this.savedCustomers.removeIf(customerEntity -> customerEntity.getId().equals(customerId));
    }

    @Override
    public CustomerEntity save(CustomerEntity customer){
        if(customer.getId() == null){
            customer.setId(NEXT_ID);
            NEXT_ID++;
            this.savedCustomers.add(customer);
        }
        return customer;
    }

    @Override
    public int count(){return this.savedCustomers.size();}

    @Override
    public boolean existsByUsername(String username){
        return this.savedCustomers
                .stream()
                .anyMatch(customerEntity -> customerEntity.getUsername().equals(username));
    }
}
