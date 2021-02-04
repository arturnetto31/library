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

import static com.phoebus.library.userlibrary.builders.UserLibraryBuilderDTO.createUserLibraryDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Tests to verify if could get page of User Library")
public class ListPageUserServiceTest {

    @Mock
    UserLibraryRepository repository;

    public ListPageUserServiceImpl listPageUserService;

    @BeforeEach
    void setUp() {
        this.listPageUserService = new ListPageUserServiceImpl(repository);
    }

    @Test
    @DisplayName("Should page the users")
    void shouldPageUsers() {

        Pageable pageable = PageRequest.of(0,2);

        UserLibraryDTO userLibrary = createUserLibraryDTO().build();
        when(listPageUserService.listUserOnPage(pageable))
                .thenReturn(new PageImpl<>(Collections.nCopies(2, createUserLibraryDTO().build())));


        Page<UserLibraryDTO> result = listPageUserService.listUserOnPage(pageable);
        assertAll("Books",
                () -> assertThat(result.getNumber(), is(0)),
                () -> assertThat(result.getSize(), is(2)),

                () -> assertThat(result.getContent().get(0).getName(), is(userLibrary.getName())),
                () -> assertThat(result.getContent().get(0).getAge(), is(userLibrary.getAge())),
                () -> assertThat(result.getContent().get(0).getPhone(), is(userLibrary.getPhone())),
                () -> assertThat(result.getContent().get(0).getEmail(), is(userLibrary.getEmail())),
                () -> assertThat(result.getContent().get(0).getGender(), is(userLibrary.getGender())),

                () -> assertThat(result.getContent().get(1).getName(), is(userLibrary.getName())),
                () -> assertThat(result.getContent().get(1).getAge(), is(userLibrary.getAge())),
                () -> assertThat(result.getContent().get(1).getPhone(), is(userLibrary.getPhone())),
                () -> assertThat(result.getContent().get(1).getEmail(), is(userLibrary.getEmail())),
                () -> assertThat(result.getContent().get(1).getGender(), is(userLibrary.getGender()))


                );
    }

}
