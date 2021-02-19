package com.phoebus.library.book;


import com.phoebus.library.book.service.ListPageBookServiceImpl;
import com.phoebus.library.categoryofbook.CategoryOfBook;
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
import java.util.HashSet;
import java.util.Set;

import static com.phoebus.library.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Test to verify if could list a page of book")
public class ListPageBookServiceTest {
    @Mock
    private BookRepository repository;

    private ListPageBookServiceImpl listPageBookServiceImpl;

    @BeforeEach
    void setUp() {
        this.listPageBookServiceImpl = new ListPageBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Should return a page of book")
    void shouldGetPageBook() {
        Pageable pageable = PageRequest.of(0,2);

        CategoryOfBook category = new CategoryOfBook(1L,"action");
        Set<CategoryOfBook> categoryOfBookSet = new HashSet<>();
        categoryOfBookSet.add(category);

        when(repository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.nCopies(2, createBook().category(categoryOfBookSet).build())));
        Page<BookDTO> result = listPageBookServiceImpl.listBookOnPage(pageable);

        assertAll("Book",
                ()-> assertThat(result.getNumber(), is(0)),
                ()-> assertThat(result.getTotalElements(), is(2L)),
                ()-> assertThat(result.getTotalPages(), is(1)),
                ()-> assertThat(result.getSize(), is(2)),

                () -> assertThat(result.getContent().get(0).getTitle(), is("teste book")),
                () -> assertThat(result.getContent().get(0).getSynopsis(), is("test")),
                () -> assertThat(result.getContent().get(0).getIsbn(), is("0000")),
                () -> assertThat(result.getContent().get(0).getAuthor(), is("teste")),
                () -> assertThat(result.getContent().get(0).getPrice(), is(150.2)),
                () -> assertThat(result.getContent().get(0).getQuantityAvailable(), is(2)),
                () -> assertThat(result.getContent().get(0).getCategory().contains("action"), is(true)),


                () -> assertThat(result.getContent().get(1).getTitle(), is("teste book")),
                () -> assertThat(result.getContent().get(1).getSynopsis(), is("test")),
                () -> assertThat(result.getContent().get(1).getIsbn(), is("0000")),
                () -> assertThat(result.getContent().get(1).getAuthor(), is("teste")),
                () -> assertThat(result.getContent().get(1).getPrice(), is(150.2)),
                () -> assertThat(result.getContent().get(1).getQuantityAvailable(), is(2)),
                () -> assertThat(result.getContent().get(1).getCategory().contains("action"), is(true))

                );
    }
}
