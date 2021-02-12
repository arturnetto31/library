package com.phoebus.library.book;


import com.phoebus.library.book.service.ListAllBooksByCategoryImpl;
import com.phoebus.library.categoryofbook.CategoryOfBook;
import com.phoebus.library.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.phoebus.library.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Test to verify if could get all books by category")
public class ListAllBooksByCategoryTest {

    @Mock
    private BookRepository repository;

    private ListAllBooksByCategoryImpl listAllBooksByCategoryImpl;

    @BeforeEach
    void setUp() {
        this.listAllBooksByCategoryImpl = new ListAllBooksByCategoryImpl(repository);
    }

    @Test
    @DisplayName("Should returns a list with books by category")
    void shouldListAllBooksByCategory() {
        List<CategoryOfBook> category = new ArrayList<>();
        List<Book> allBooks = new ArrayList<>();

        Book book = createBook().category(category).build();
        category.add(new CategoryOfBook(1L,"categoryTest"));
        allBooks.add(book);

        String categoryTest = "categoryTest";
        when(repository.findAll()).thenReturn(allBooks);

        List<BookDTO> result = this.listAllBooksByCategoryImpl.listAllBooksByCategory(categoryTest);

        assertAll("Category of Books",
                () -> assertThat(result.get(0).getTitle(), is("teste book")),
                () -> assertThat(result.get(0).getSynopsis(), is("test")),
                () -> assertThat(result.get(0).getIsbn(), is("0000")),
                () -> assertThat(result.get(0).getAuthor(), is("teste")),
                () -> assertThat(result.get(0).getPrice(), is(150.2)),
                () -> assertThat(result.get(0).getQuantityAvailable(), is(2)),
                () -> assertThat(result.get(0).getCategory(), is(category))
                );
    }

    @Test
    @DisplayName("Should not found a list of book by category")
    void shouldNotListAllBooksByCategory() {
        List<CategoryOfBook> categoryBook = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();
        bookList.add(createBook().category(categoryBook).build());
        String category = "categoryTest";

        when(repository.findAll()).thenReturn(bookList);

        Assertions.assertThrows(BookNotFoundException.class, () -> listAllBooksByCategoryImpl.listAllBooksByCategory(category));
    }
}
