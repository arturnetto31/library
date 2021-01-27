package com.phoebus.library.userlibrary;


import com.phoebus.library.exceptions.UserNotFoundException;
import com.phoebus.library.userlibrary.service.GetUserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to know if the function of find User Library by ID it's valid")
public class GetUserServiceTest {

    @Mock
    UserLibraryRepository repository;

    public GetUserServiceImpl getUserService;

    @BeforeEach
    void setUp() {
        this.getUserService = new GetUserServiceImpl(repository);
    }

    @Test
    @DisplayName("Should returns an User Library")
    void shouldGetUser() {

        when(repository.existsById(anyLong())).thenReturn(true);

        getUserService.getUserLibrary(1L);

        verify(repository).findById(anyLong());
    }

    @Test
    @DisplayName("Shouldn't returns an User Library and throws User Library Not Found Exception")
    void shouldNotFoundExceptionGetUser() {

        when(repository.existsById(anyLong())).thenReturn(false);

        Assertions.assertThrows(UserNotFoundException.class, () -> getUserService.getUserLibrary(1L));

        verify(repository,times(0)).findById(anyLong());
    }
}
