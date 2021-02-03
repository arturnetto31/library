package com.phoebus.library.userlibrary;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoebus.library.userlibrary.service.*;
import com.phoebus.library.userlibrary.v1.UserLibraryControllerV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.phoebus.library.userlibrary.builders.UserLibraryBuilderDTO.createUserLibraryDTO;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserLibraryControllerV1.class)
@DisplayName("Verify if the controller could do all of the tasks")
public class UserLibraryControllerV1Test {

    private final String URL_USERLIBRARY = "v1/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeleteUserService deleteUserService;

    @MockBean
    private EditUserService editUserService;

    @MockBean
    private GetUserService getUserService;

    @MockBean
    private ListPageUserService listPageUserService;

    @MockBean
    private ListUserService listUserService;

    @MockBean
    private SaveUserService saveUserService;

    @Test
    @DisplayName("Test to delete an user library when successful")
    void shouldDeleteUserById() throws Exception {
        mockMvc.perform(delete(URL_USERLIBRARY + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteUserService).delete(1L);
    }

    @Test
    @DisplayName("Test to edit an user library when successful")
    void shouldAttUser() throws Exception {
        Long id = 1L;
        UserLibraryDTO userLibraryDTO = createUserLibraryDTO().id(id).build();
        userLibraryDTO.setAge(10);

        mockMvc.perform(put(URL_USERLIBRARY + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(userLibraryDTO)))
                .andDo(print())
                .andExpect(status().isNoContent());

        ArgumentCaptor<UserLibraryDTO> captorUserDTO = ArgumentCaptor.forClass(UserLibraryDTO.class);
        ArgumentCaptor<Long> captorLong = ArgumentCaptor.forClass(Long.class);
        verify(editUserService).editUserLibrary(captorLong.capture(), captorUserDTO.capture());

        UserLibraryDTO result = captorUserDTO.getValue();

        assertThat(captorLong.getValue()).isEqualTo(id);
        assertThat(result.getId()).isEqualTo(userLibraryDTO.getId());
        assertThat(result.getPhone()).isEqualTo(userLibraryDTO.getPhone());
        assertThat(result.getName()).isEqualTo(userLibraryDTO.getName());
        assertThat(result.getAge()).isEqualTo(userLibraryDTO.getAge());
        assertThat(result.getGender()).isEqualTo(userLibraryDTO.getGender());
        assertThat(result.getEmail()).isEqualTo(userLibraryDTO.getEmail());

    }
    
}
