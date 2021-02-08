package com.phoebus.library.userlibrary;

import com.phoebus.library.userlibrary.service.ListUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.phoebus.library.userlibrary.builders.UserLibraryBuilder.createUserLibrary;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Test to verify if the system could be List all of Users Library")
public class ListUserServiceTest {

    @Mock
    private UserLibraryRepository repository;

    private ListUserServiceImpl listUserService;

    @BeforeEach
    void setUp() {
        this.listUserService = new ListUserServiceImpl(repository);
    }


    @Test
    @DisplayName("Should returns a List with all Users Library")
    void shouldGetListUsers() {
        UserLibrary userLibrary = createUserLibrary().build();
        when(repository.findAll()).thenReturn(Stream.of(createUserLibrary().build()).collect(Collectors.toList()));

        List<UserLibraryDTO> result = this.listUserService.listUserLibrary();

        assertAll("UserLibrary",
                () -> assertThat(result.size(), is(1)),
                () -> assertThat(result.get(0).getName(),is ("Test")),
                () -> assertThat(result.get(0).getAge(),is(22)),
                () -> assertThat(result.get(0).getEmail(),is("teste@teste.com")),
                () -> assertThat(result.get(0).getPhone(),is("0000-0000")),
                () -> assertThat(result.get(0).getGender(), is("M"))
        );
    }
}
