package com.phoebus.library.userlibrary;


import com.phoebus.library.exceptions.UserInconsistencyInDataException;
import com.phoebus.library.userlibrary.service.SaveUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.phoebus.library.userlibrary.builders.UserLibraryBuilder.createUserLibrary;
import static com.phoebus.library.userlibrary.builders.UserLibraryBuilderDTO.createUserLibraryDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify if the system could be save an User Library")
public class SaveUserServiceTest {

    @Mock
    UserLibraryRepository repository;

    public SaveUserServiceImpl saveUserService;

    @BeforeEach
    void setUp() {
        this.saveUserService = new SaveUserServiceImpl(repository);
    }

    @Test
    @DisplayName("Should save an User Library")
    void shouldSaveUser() {

        UserLibrary userLibrary = createUserLibrary().build();
        UserLibraryDTO userLibraryDTO = createUserLibraryDTO().build();
        UserLibrary saveThatUser = userLibrary.to(userLibraryDTO);
        saveUserService.save(saveThatUser);

        ArgumentCaptor<UserLibrary> captorUser = ArgumentCaptor.forClass(UserLibrary.class);
        verify(repository).save(captorUser.capture());

        UserLibrary result = captorUser.getValue();

        assertAll("User Library",
                () -> assertThat(result.getName(), is(userLibraryDTO.getName())),
                () -> assertThat(result.getAge(), is(userLibraryDTO.getAge())),
                () -> assertThat(result.getEmail(), is(userLibraryDTO.getEmail())),
                () -> assertThat(result.getPhone(), is(userLibraryDTO.getPhone())),
                () -> assertThat(result.getGender(),is(userLibraryDTO.getGender()))
                );

    }

    @Test
    @DisplayName("Shouldn't save an User Library and Throws an Exception")
    void shouldNotSaveUser() {
        when(repository.save(any())).thenThrow(new UserInconsistencyInDataException());

        assertThrows(UserInconsistencyInDataException.class,() -> saveUserService.save(any()));
        verify(repository, times(0)).save(any());
    }
}
