package com.phoebus.library.book;


import com.phoebus.library.book.service.ListBookServiceImpl;
import com.phoebus.library.categoryofbook.CategoryOfBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.phoebus.library.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Test to verify if could get a list with all books")
public class ListBookServiceTest {
    @Mock
    private BookRepository repository;

    private ListBookServiceImpl listBookServiceImpl;

    @BeforeEach
    void setUp() {
        this.listBookServiceImpl = new ListBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Should return a list with all books")
    void shouldGetListBook() {
        List<Book> bookList = new ArrayList<>();

        CategoryOfBook category = new CategoryOfBook(1L,"action");
        Set<CategoryOfBook> categoryOfBookSet = new HashSet<>();
        categoryOfBookSet.add(category);

        Book book = createBook().category(categoryOfBookSet).build();
        bookList.add(book);

        when(repository.findAll()).thenReturn(bookList);

        List<BookDTO> result = this.listBookServiceImpl.listBooks();

        assertAll("Book",
                () -> assertThat(result.size(), is(1)),
                () -> assertThat(result.get(0).getTitle(), is("teste book")),
                () -> assertThat(result.get(0).getSynopsis(), is("test")),
                () -> assertThat(result.get(0).getIsbn(), is("0000")),
                () -> assertThat(result.get(0).getAuthor(), is("teste")),
                () -> assertThat(result.get(0).getPrice(), is(150.2)),
                () -> assertThat(result.get(0).getQuantityAvailable(), is(2)),
                () -> assertThat(result.get(0).getCategory(), is(categoryOfBookSet))
                );

    }

}
