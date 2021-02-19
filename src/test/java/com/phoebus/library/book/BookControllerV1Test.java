package com.phoebus.library.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoebus.library.book.service.DeleteBookService;
import com.phoebus.library.book.service.EditBookService;
import com.phoebus.library.book.service.GetBookService;
import com.phoebus.library.book.service.ListAllBooksByCategory;
import com.phoebus.library.book.service.ListBookService;
import com.phoebus.library.book.service.ListPageBookService;
import com.phoebus.library.book.service.SaveBookService;
import com.phoebus.library.book.v1.BookControllerV1;
import com.phoebus.library.categoryofbook.CategoryOfBook;
import com.phoebus.library.exceptions.BookNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.phoebus.library.book.builders.BookBuilderDTO.createBookDTO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
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
@WebMvcTest(BookControllerV1.class)
@DisplayName("Verify if the controller could do all the tasks")
public class BookControllerV1Test {

    private final String URL_BOOK = "/v1/book";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeleteBookService deleteBookService;

    @MockBean
    private EditBookService editBookService;

    @MockBean
    private GetBookService getBookService;

    @MockBean
    private ListPageBookService listPageBookService;

    @MockBean
    private ListAllBooksByCategory listAllBooksByCategory;

    @MockBean
    private ListBookService listBookService;

    @MockBean
    private SaveBookService saveBookService;

    @Test
    @DisplayName("Test to verify if controller could do the task of delete when successful")
    void shouldDeleteBookById() throws Exception {
        mockMvc.perform(delete(URL_BOOK + "/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isNoContent());

        verify(deleteBookService).delete(1L);
    }

    @Test
    @DisplayName("Test to edit a book when successful")
    void shouldAttBook() throws Exception {
       mockMvc.perform(put(URL_BOOK + "/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content(readJson("bookUpdate.json")))
               .andDo(print())
               .andExpect(status().isNoContent());

       verify(editBookService).editBook(eq(1L), any(BookDTO.class));
    }
    @Test
    @DisplayName("Test to get a book by id when successful")
    void shouldGetBookById() throws Exception {

        BookDTO bookDTO = createBookDTO().build();

        when(getBookService.getBookDTO(anyLong())).thenReturn(bookDTO);

        mockMvc.perform(get(URL_BOOK + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("teste book")))
                .andExpect(jsonPath("$.synopsis", is("test")))
                .andExpect(jsonPath("$.isbn", is("0000")))
                .andExpect(jsonPath("$.author", is("teste")))
                .andExpect(jsonPath("$.price", is(150.2)))
                .andExpect(jsonPath("$.quantityAvailable", is(2)))
                .andExpect(jsonPath("$.category.[0].id", is(1)))
                .andExpect(jsonPath("$.category.[0].name", is("action")));

        verify(getBookService).getBookDTO(1L);
    }

    @Test
    @DisplayName("Test to verify if throws an exception of couldn't find book by id")
    void shouldThrowsNotFoundBookById() throws Exception{
        when(getBookService.getBookDTO(anyLong())).thenThrow(new BookNotFoundException());

        mockMvc.perform(get(URL_BOOK + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getBookService).getBookDTO(1L);
    }

    @Test
    @DisplayName("Test to verify if could get a list page of book")
    void shouldGetAListPage() throws Exception {
        Page<BookDTO> bookDTOPage = new PageImpl<>(Collections.singletonList(createBookDTO().build()));

        Pageable pageable = PageRequest.of(0,2);

        when(listPageBookService.listBookOnPage(pageable)).thenReturn(bookDTOPage);

        mockMvc.perform(get(URL_BOOK + "/page/?page=0&size=2").accept(MediaType.APPLICATION_JSON)
        .characterEncoding("utf-8"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content[0].id", is(1)))
        .andExpect(jsonPath("$.content[0].title", is("teste book")))
        .andExpect(jsonPath("$.content[0].synopsis", is("test")))
        .andExpect(jsonPath("$.content[0].isbn", is("0000")))
        .andExpect(jsonPath("$.content[0].author", is("teste")))
        .andExpect(jsonPath("$.content[0].price", is(150.2)))
        .andExpect(jsonPath("$.content[0].quantityAvailable", is(2)));

        verify(listPageBookService).listBookOnPage(pageable);
    }

    @Test
    @DisplayName("Test to list all books")
    void shouldListBooks() throws Exception {
        BookDTO bookDTO = createBookDTO().id(1L).build();
        BookDTO bookDTO2 = createBookDTO().id(2L).build();

        List<BookDTO> listBooks = Arrays.asList(bookDTO,bookDTO2);

        when(listBookService.listBooks()).thenReturn(listBooks);

        MvcResult mvcResult = mockMvc.perform(get(URL_BOOK).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[*]", hasSize(2))).andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(listBooks))
                .isEqualToIgnoringWhitespace(resultResponseBody);

        verify(listBookService).listBooks();
    }

    @Test
    @DisplayName("Test to verify if could list book by category")
    void shouldFindListBookByCategory() throws Exception {
        CategoryOfBook category = new CategoryOfBook(1L,"action");
        Set<CategoryOfBook> categoryOfBookSet = new HashSet<>();
        categoryOfBookSet.add(category);

        BookDTO bookDTO = createBookDTO().id(1L).category(categoryOfBookSet).build();
        BookDTO bookDTO2 = createBookDTO().id(2L).category(categoryOfBookSet).build();

        List<BookDTO> bookDTOList = Arrays.asList(bookDTO,bookDTO2);

        when(listAllBooksByCategory.listAllBooksByCategory("action")).thenReturn(bookDTOList);

        MvcResult mvcResult = mockMvc.perform(get(URL_BOOK + "/listbook/{category}", "action").accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[*]",hasSize(2))).andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(bookDTOList)).isEqualToIgnoringWhitespace(resultResponseBody);

        verify(listAllBooksByCategory).listAllBooksByCategory("action");
    }
    @Test
    @DisplayName("Test to verify if could save a book when successful")
    void shouldSaveBook() throws Exception {
       mockMvc.perform(post(URL_BOOK).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
       .content(readJson("bookDTO.json")))
               .andDo(print())
               .andExpect(status().isCreated());

       verify(saveBookService).save(any(BookDTO.class));

    }

    @Test
    @DisplayName("Should try to save without tittle")
    void shouldTrySaveWithoutName() throws Exception {
        BookDTO bookDTO = createBookDTO().title("").build();

        mockMvc.perform(post(URL_BOOK)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(bookDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should try to save without synopsis")
    void shouldTrySaveWithoutSynopsis() throws Exception {
        BookDTO bookDTO = createBookDTO().synopsis("").build();

        mockMvc.perform(post(URL_BOOK)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should try to save without author")
    void shouldTrySaveWithoutAuthor() throws Exception {
        BookDTO bookDTO = createBookDTO().author("").build();

        mockMvc.perform(post(URL_BOOK)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should try to save without isbn")
    void shouldTrySaveWithoutIsbn() throws Exception {
        BookDTO bookDTO = createBookDTO().isbn("").build();

        mockMvc.perform(post(URL_BOOK)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should try to save with negative price")
    void shouldTrySaveWithNegativePrice() throws Exception {
        BookDTO bookDTO = createBookDTO().price(-1).build();

        mockMvc.perform(post(URL_BOOK)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should try to save with negative quantity")
    void shouldTrySaveWithNegativeQuantity() throws Exception {
        BookDTO bookDTO = createBookDTO().quantityAvailable(-1).build();

        mockMvc.perform(post(URL_BOOK)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/dataJson/" + file).toAbsolutePath());
        return new String(bytes);
    }

}
