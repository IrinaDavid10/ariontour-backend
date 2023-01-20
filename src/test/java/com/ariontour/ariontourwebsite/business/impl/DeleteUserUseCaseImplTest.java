package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.persistance.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    void deleteUser_shouldDeleteUserById() {
        when(userRepositoryMock.existsById(1L))
                .thenReturn(true);
        Boolean answer = deleteUserUseCase.deleteUser(1L);
        assertTrue(answer);
        verify(userRepositoryMock).deleteById(1L);
    }
    @Test
    void deleteUser_shouldReturnFalseIfUserDoesNotExist() {
        when(userRepositoryMock.existsById(1L))
                .thenReturn(false);
        Boolean answer = deleteUserUseCase.deleteUser(1L);
        assertFalse(answer);
        verify(userRepositoryMock, never()).deleteById(1L);
    }
}
