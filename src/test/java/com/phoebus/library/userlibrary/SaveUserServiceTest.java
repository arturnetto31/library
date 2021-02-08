package com.phoebus.library.userlibrary;

import com.phoebus.library.userlibrary.service.SaveUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.phoebus.library.userlibrary.builders.UserLibraryBuilderDTO.createUserLibraryDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify if the system could be save an User Library")
public class SaveUserServiceTest {

    @Mock
    private UserLibraryRepository repository;

    private SaveUserServiceImpl saveUserService;

    @BeforeEach
    void setUp() {
        this.saveUserService = new SaveUserServiceImpl(repository);
    }

    @Test
    @DisplayName("Should save an User Library")
    void shouldSaveUser() {

        UserLibraryDTO saveThatUser = createUserLibraryDTO().build();
        saveUserService.save(saveThatUser);

        ArgumentCaptor<UserLibrary> captorUser = ArgumentCaptor.forClass(UserLibrary.class);
        verify(repository, times(1)).save(captorUser.capture());

        UserLibrary result = captorUser.getValue();

        assertAll("User Library",

                () -> assertThat(result.getName(), is("Test")),
                () -> assertThat(result.getAge(), is(22)),
                () -> assertThat(result.getEmail(), is("teste@teste.com")),
                () -> assertThat(result.getPhone(), is("0000-0000")),
                () -> assertThat(result.getGender(),is("M"))
                );

    }
/*
    @Test
    @DisplayName("Shouldn't save an User Library and Throws an Exception")
    void shouldNotSaveUser() {
        when(repository.save(any())).thenThrow(new UserInconsistencyInDataException());

        assertThrows(UserInconsistencyInDataException.class,() -> saveUserService.save(any()));
        verify(repository, times(0)).save(any());
    }
*/
}
