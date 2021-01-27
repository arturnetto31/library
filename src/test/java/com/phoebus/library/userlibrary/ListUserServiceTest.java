package com.phoebus.library.userlibrary;


import com.phoebus.library.userlibrary.service.ListUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify if the system could be List all of Users Library")
public class ListUserServiceTest {

    @Mock
    UserLibraryRepository repository;

    public ListUserServiceImpl listUserService;

    @BeforeEach
    void setUp() {
        this.listUserService = new ListUserServiceImpl(repository);
    }
    @Test
    @DisplayName("Should returns a List with all Users Library")
    void shouldGetListUsers() {

    }
}
