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

import java.util.Optional;

import static com.phoebus.library.userlibrary.builders.UserLibraryBuilder.createUserLibrary;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
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

        UserLibrary userLibrary = createUserLibrary().build();
        Optional<UserLibrary> userLibraryOptional = Optional.of(userLibrary);
        when(repository.findById(anyLong())).thenReturn(userLibraryOptional);

        UserLibraryDTO result = this.getUserService.getUserLibrary(1L);

        assertAll("Test",
                () -> assertThat(result.getName(), is(userLibrary.getName())),
                () -> assertThat(result.getAge(), is(userLibrary.getAge())),
                () -> assertThat(result.getEmail(), is(userLibrary.getEmail())),
                () -> assertThat(result.getPhone(), is(userLibrary.getPhone())),
                () -> assertThat(result.getGender(), is(userLibrary.getGender()))
        );
    }

    @Test
    @DisplayName("Shouldn't returns an User Library and throws User Library Not Found Exception")
    void shouldNotFoundExceptionGetUser() {

        when(repository.existsById(anyLong())).thenReturn(false);

        Assertions.assertThrows(UserNotFoundException.class, () -> getUserService.getUserLibrary(1L));

        verify(repository,times(0)).findById(anyLong());
    }
}
