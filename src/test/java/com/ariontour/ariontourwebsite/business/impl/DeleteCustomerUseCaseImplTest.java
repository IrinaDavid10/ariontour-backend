package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.domain.DeleteCustomerRequest;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteCustomerUseCaseImplTest {
    @Mock
    private CustomerRepository customerRepositoryMock;
    @InjectMocks
    private DeleteCustomerUseCaseImpl deleteCustomerUseCase;

    @Test
    void deleteCustomer_shouldDeleteCustomerById() {
        when(customerRepositoryMock.existsById(1L))
                .thenReturn(true);
        DeleteCustomerRequest newDeleteRequest = DeleteCustomerRequest.builder()
                .id(1L)
                .build();
        Boolean answer = deleteCustomerUseCase.deleteCustomer(newDeleteRequest);
        assertTrue(answer);
        verify(customerRepositoryMock).deleteById(newDeleteRequest.getId());

    }
}