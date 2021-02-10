package com.phoebus.library.userlibrary;

import com.phoebus.library.userlibrary.service.ListPageUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static com.phoebus.library.userlibrary.builders.UserLibraryBuilder.createUserLibrary;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Tests to verify if could get page of User Library")
public class ListPageUserServiceTest {

    @Mock
    private UserLibraryRepository repository;

    private ListPageUserServiceImpl listPageUserService;

    @BeforeEach
    void setUp() {
        this.listPageUserService = new ListPageUserServiceImpl(repository);
    }

    @Test
    @DisplayName("Should page the users")
    void shouldPageUsers() {

        Pageable pageable = PageRequest.of(0,2);
        when(repository.findAll(pageable))
                .thenReturn(new PageImpl<>(Collections.nCopies(2, createUserLibrary().build())));

        Page<UserLibraryDTO> result = listPageUserService.listUserOnPage(pageable);

        assertAll("User",
                ()-> assertThat(result.getNumber(), is(0)),
                ()-> assertThat(result.getTotalElements(), is(2L)),
                ()-> assertThat(result.getTotalPages(), is(1)),
                ()-> assertThat(result.getSize(), is(2)),

                () -> assertThat(result.getContent().get(0).getName(), is("Test")),
                () -> assertThat(result.getContent().get(0).getAge(), is(22)),
                () -> assertThat(result.getContent().get(0).getPhone(), is("0000-0000")),
                () -> assertThat(result.getContent().get(0).getEmail(), is("teste@teste.com")),
                () -> assertThat(result.getContent().get(0).getGender(), is("M")),

                () -> assertThat(result.getContent().get(1).getName(),is("Test")),
                () -> assertThat(result.getContent().get(1).getAge(), is(22)),
                () -> assertThat(result.getContent().get(1).getPhone(), is("0000-0000")),
                () -> assertThat(result.getContent().get(1).getEmail(), is("teste@teste.com")),
                () -> assertThat(result.getContent().get(1).getGender(), is("M"))


                );
        //verify(repository.findAll(pageable));
    }

}
