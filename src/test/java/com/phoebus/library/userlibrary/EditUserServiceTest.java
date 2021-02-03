package com.phoebus.library.userlibrary;


import com.phoebus.library.exceptions.UserInconsistencyInDataException;
import com.phoebus.library.userlibrary.service.EditUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.phoebus.library.userlibrary.builders.UserLibraryBuilder.createUserLibrary;
import static com.phoebus.library.userlibrary.builders.UserLibraryBuilderDTO.createUserLibraryDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Test to verify if it's Editing an User Library")
public class EditUserServiceTest {

    @Mock
    UserLibraryRepository repository;

    public EditUserServiceImpl editUserServiceImpl;
    @BeforeEach
    void setUp() {
        this.editUserServiceImpl = new EditUserServiceImpl(repository);
    }

    @Test
    @DisplayName("Should returns an Edited User Library")
    void shouldEditAnUser() {
        UserLibraryDTO userLibraryDTO = createUserLibraryDTO().name("Teste").build();
        UserLibrary userLibrary = createUserLibrary().build();

        Optional<UserLibrary> userLibraryOptional = Optional.of(userLibrary);
        when(repository.findById(anyLong())).thenReturn(userLibraryOptional);

        editUserServiceImpl.editUserLibrary(1L, userLibraryDTO);

        ArgumentCaptor<UserLibrary> captorUserLibrary = ArgumentCaptor.forClass(UserLibrary.class);
        verify(repository).save(captorUserLibrary.capture());

        UserLibrary result = captorUserLibrary.getValue();

        assertAll("User",
                () -> assertThat(result.getName(), is(userLibraryDTO.getName())),
                () -> assertThat(result.getAge(), is(userLibraryDTO.getAge())),
                () -> assertThat(result.getPhone(), is(userLibraryDTO.getPhone())),
                () -> assertThat(result.getEmail(), is(userLibraryDTO.getEmail())),
                () -> assertThat(result.getGender(), is(userLibraryDTO.getGender()))
        );
    }



    @Test
    @DisplayName("Should throws an Exception of Edit an User Library")
    void shouldThrowsExceptionEditAnUser() {
        UserLibraryDTO userLibraryDTO = createUserLibraryDTO().name("Teste").build();
        UserLibrary userLibrary = createUserLibrary().build();

        Optional<UserLibrary> userLibraryOptional = Optional.of(userLibrary);
        when(repository.findById(anyLong())).thenThrow( new UserInconsistencyInDataException());

        assertThrows(UserInconsistencyInDataException.class, () -> editUserServiceImpl.editUserLibrary(1L, userLibraryDTO));

        verify(repository, times(0)).save(any());





    }
}
