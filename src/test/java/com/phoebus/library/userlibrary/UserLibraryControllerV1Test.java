package com.phoebus.library.userlibrary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoebus.library.exceptions.UserNotFoundException;
import com.phoebus.library.userlibrary.service.DeleteUserService;
import com.phoebus.library.userlibrary.service.EditUserService;
import com.phoebus.library.userlibrary.service.GetUserService;
import com.phoebus.library.userlibrary.service.ListPageUserService;
import com.phoebus.library.userlibrary.service.ListUserService;
import com.phoebus.library.userlibrary.service.SaveUserService;
import com.phoebus.library.userlibrary.v1.UserLibraryControllerV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.phoebus.library.userlibrary.builders.UserLibraryBuilder.createUserLibrary;
import static com.phoebus.library.userlibrary.builders.UserLibraryBuilderDTO.createUserLibraryDTO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserLibraryControllerV1.class)
@DisplayName("Verify if the controller could do all of the tasks")
public class UserLibraryControllerV1Test {

    private final String URL_USERLIBRARY = "/v1/user";

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

    @Test
    @DisplayName("Test to get an User Library by id")
    void shouldGetAnUserID() throws Exception{

        when(getUserService.getUserLibrary(anyLong())).thenReturn(createUserLibraryDTO().build());

        mockMvc.perform(get(URL_USERLIBRARY + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test")))
                .andExpect(jsonPath("$.age", is(22)))
                .andExpect(jsonPath("$.email", is("teste@teste.com")))
                .andExpect(jsonPath("$.phone", is("0000-0000")))
                .andExpect(jsonPath("$.gender", is("M")));

        verify(getUserService).getUserLibrary(1L);
    }

    @Test
    @DisplayName("Test to throws an Exception when the test try to find an User Library by id")
    void shouldThrowsNotFoundUserID() throws Exception{
        when(getUserService.getUserLibrary(anyLong())).thenThrow(new UserNotFoundException());

        mockMvc.perform(get(URL_USERLIBRARY + "/{id}", 1L).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getUserService).getUserLibrary(1L);
    }

    @Test
    @DisplayName("Test to list in a page of users library when succesfull")
    void shouldGetAListPage() throws Exception {

        Page<UserLibraryDTO> userLibraryDTOPage = new PageImpl<>(Collections.singletonList(createUserLibraryDTO().build()));

        Pageable pageable = PageRequest.of(0,2);

        when(listPageUserService.listUserOnPage(pageable)).thenReturn(userLibraryDTOPage);

        mockMvc.perform(get(URL_USERLIBRARY + "/page/?page=0&size=2").accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id",   is(1)))
                .andExpect(jsonPath("$.content[0].name", is("Test")))
                .andExpect(jsonPath("$.content[0].age",  is(22)))
                .andExpect(jsonPath("$.content[0].email",is("teste@teste.com")))
                .andExpect(jsonPath("$.content[0].phone",is("0000-0000")))
                .andExpect(jsonPath("$.content[0].gender", is("M")));

        verify(listPageUserService).listUserOnPage(pageable);

    }

    @Test
    @DisplayName("Test to list all users library")
    void shouldListUsers() throws Exception {
      UserLibraryDTO userLibraryDTO = createUserLibraryDTO().id(1L).name("user 1").build();
      UserLibraryDTO userLibraryDTO2 = createUserLibraryDTO().id(2L).name("user 2").build();

      List<UserLibraryDTO> userLibraryDTOList = Arrays.asList(userLibraryDTO,userLibraryDTO2);

      when(listUserService.listUserLibrary()).thenReturn(userLibraryDTOList);

        MvcResult mvcResult = mockMvc.perform(get(URL_USERLIBRARY).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(2))).andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(userLibraryDTOList))
                .isEqualToIgnoringWhitespace(resultResponseBody);

        verify(listUserService).listUserLibrary();
    }

    @Test
    @DisplayName("Test to save an User Library when successful")
    void shouldSaveAnUser() throws Exception {
        UserLibraryDTO userLibrary = createUserLibraryDTO().build();

        mockMvc.perform(post(URL_USERLIBRARY).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(userLibrary)))
                .andDo(print())
                .andExpect(status().isCreated());

        ArgumentCaptor<UserLibraryDTO> captorUser = ArgumentCaptor.forClass(UserLibraryDTO.class);
        verify(saveUserService).save(captorUser.capture());
        UserLibraryDTO result = captorUser.getValue();

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Test");
        assertThat(result.getAge()).isEqualTo(22);
        assertThat(result.getEmail()).isEqualTo("teste@teste.com");
        assertThat(result.getPhone()).isEqualTo("0000-0000");
        assertThat(result.getGender()).isEqualTo("M");

    }

    @Test
    @DisplayName("Test to try to save without name")
    void shouldTryToSaveWithoutName() throws Exception {
        UserLibraryDTO userLibraryDTO = createUserLibraryDTO().id(1L).name("").build();

        mockMvc.perform(post(URL_USERLIBRARY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userLibraryDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Test to try to save without phone")
    void shouldTryToSaveWithoutPhone() throws Exception {
        UserLibrary userLibrary = createUserLibrary().id(1L).phone("").build();

        mockMvc.perform(post(URL_USERLIBRARY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userLibrary)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test to try to save without email")
    void shouldTryToSaveWithoutEmail() throws Exception {
        UserLibrary userLibrary = createUserLibrary().id(1L).email("").build();

        mockMvc.perform(post(URL_USERLIBRARY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userLibrary)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


}
