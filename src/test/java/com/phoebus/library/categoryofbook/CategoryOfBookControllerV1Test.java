package com.phoebus.library.categoryofbook;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoebus.library.categoryofbook.service.DeleteCategoryOfBookService;
import com.phoebus.library.categoryofbook.service.EditCategoryOfBookService;
import com.phoebus.library.categoryofbook.service.GetCategoryOfBookService;
import com.phoebus.library.categoryofbook.service.ListCategoryOfBookService;
import com.phoebus.library.categoryofbook.service.ListPageCategoryOfBookService;
import com.phoebus.library.categoryofbook.service.SaveCategoryOfBook;
import com.phoebus.library.categoryofbook.v1.CategoryOfBookControllerV1;
import com.phoebus.library.exceptions.CategoryOfBookNotFoundException;
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

import static com.phoebus.library.categoryofbook.builders.CategoryOfBookBuilderDTO.createCategoryOfBookDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
@WebMvcTest(CategoryOfBookControllerV1.class)
@DisplayName("Test the controller of category of book")
public class CategoryOfBookControllerV1Test {

    private final String URL_CATEGORYBOOK = "/v1/categorybook";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeleteCategoryOfBookService deleteCategoryOfBookService;

    @MockBean
    private EditCategoryOfBookService editCategoryOfBookService;

    @MockBean
    private GetCategoryOfBookService getCategoryOfBookService;

    @MockBean
    private ListCategoryOfBookService listCategoryOfBookService;

    @MockBean
    private ListPageCategoryOfBookService listPageCategoryOfBookService;

    @MockBean
    private SaveCategoryOfBook saveCategoryOfBook;

    @Test
    @DisplayName("Should delete a category of book by id")
    void shouldDeleteCategoryBook() throws Exception{
        mockMvc.perform(delete(URL_CATEGORYBOOK + "/{id}",1L).characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteCategoryOfBookService).delete(1L);
    }

    @Test
    @DisplayName("Should edit a category of book")
    void shouldEditCategoryBook() throws Exception{
        CategoryOfBookDTO categoryOfBookDTO = createCategoryOfBookDTO().build();

        mockMvc.perform(put(URL_CATEGORYBOOK + "/{id}",1L).characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryOfBookDTO)))
                .andDo(print())
                .andExpect(status().isOk());

        ArgumentCaptor<CategoryOfBookDTO> captorCategoryBookDTO = ArgumentCaptor.forClass(CategoryOfBookDTO.class);
        ArgumentCaptor<Long> captorLong = ArgumentCaptor.forClass(Long.class);

        verify(editCategoryOfBookService).editCategoryOfBook(captorCategoryBookDTO.capture(), captorLong.capture());

        CategoryOfBookDTO result = captorCategoryBookDTO.getValue();

        assertThat(captorLong.getValue()).isEqualTo(1L);
        assertThat(result.getId()).isEqualTo(categoryOfBookDTO.getId());
        assertThat(result.getName()).isEqualTo(categoryOfBookDTO.getName());
    }

    @Test
    @DisplayName("Should get a category of book by id")
    void shouldGetCategoryOfBookById() throws Exception {

        CategoryOfBookDTO categoryOfBookDTO = createCategoryOfBookDTO().build();

        when(getCategoryOfBookService.getCategoryOfBook(anyLong())).thenReturn(categoryOfBookDTO);

        mockMvc.perform(get(URL_CATEGORYBOOK + "/{id}", 1L).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name", is("action")));

        verify(getCategoryOfBookService).getCategoryOfBook(1L);
    }
    @Test
    @DisplayName("Should throws an exception Not Found")
    void shouldThrowsNotFoundCategoryOfBookById() throws Exception {
        when(getCategoryOfBookService.getCategoryOfBook(anyLong())).thenThrow(new CategoryOfBookNotFoundException());

        mockMvc.perform(get(URL_CATEGORYBOOK+"/{id}",1L).characterEncoding("utf-8")
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getCategoryOfBookService).getCategoryOfBook(1L);
    }
    @Test
    @DisplayName("Should get a Category of book list page ")
    void shouldGetListPageCategoryOfBook() throws Exception {
        Page<CategoryOfBookDTO> categoryOfBookDTOPage = new PageImpl<>(Collections.singletonList(createCategoryOfBookDTO().build()));

        Pageable pageable = PageRequest.of(0,2);

        when(listPageCategoryOfBookService.listPageOfCategoryOfBook(pageable)).thenReturn(categoryOfBookDTOPage);

        mockMvc.perform(get(URL_CATEGORYBOOK+"/page/?page=0&size=2").characterEncoding("utf-8")
        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].name", is("action")));

        verify(listPageCategoryOfBookService).listPageOfCategoryOfBook(pageable);
    }

    @Test
    @DisplayName("Should list all categories of book")
    void shouldListAllCategoryOfBook() throws Exception{
        List<CategoryOfBookDTO> categoryOfBookDTOList = Arrays.asList(createCategoryOfBookDTO().id(1L).build(), createCategoryOfBookDTO().id(2L).build());

        when(listCategoryOfBookService.listAllCategoriesOfBook()).thenReturn(categoryOfBookDTOList);

        MvcResult mvcResult = mockMvc.perform(get(URL_CATEGORYBOOK).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(2))).andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(categoryOfBookDTOList)).isEqualToIgnoringWhitespace(resultResponseBody);

        verify(listCategoryOfBookService).listAllCategoriesOfBook();

    }

    @Test
    @DisplayName("Should save a category of book")
    void shouldSaveCategoryOfBook() throws Exception {
        CategoryOfBookDTO categoryOfBookDTO = createCategoryOfBookDTO().build();

        mockMvc.perform(post(URL_CATEGORYBOOK).characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryOfBookDTO)))
                .andDo(print())
                .andExpect(status().isCreated());

        ArgumentCaptor<CategoryOfBookDTO> captorCategoryOfBook = ArgumentCaptor.forClass(CategoryOfBookDTO.class);

        verify(saveCategoryOfBook).saveCategoryOfBook(captorCategoryOfBook.capture());

    }

}
