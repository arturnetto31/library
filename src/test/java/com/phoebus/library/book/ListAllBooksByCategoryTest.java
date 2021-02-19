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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        CategoryOfBook category = new CategoryOfBook(1L,"action");
        Set<CategoryOfBook> categoryOfBookSet = new HashSet<>();
        categoryOfBookSet.add(category);

        List<Book> allBooks = new ArrayList<>();

        Book book = createBook().category(categoryOfBookSet).build();
        allBooks.add(book);

        when(repository.findBookByCategoryName("action")).thenReturn(allBooks);

        List<BookDTO> result = listAllBooksByCategoryImpl.listAllBooksByCategory("action");

        assertAll("Category of Books",
                () -> assertThat(result.get(0).getTitle(), is("teste book")),
                () -> assertThat(result.get(0).getSynopsis(), is("test")),
                () -> assertThat(result.get(0).getIsbn(), is("0000")),
                () -> assertThat(result.get(0).getAuthor(), is("teste")),
                () -> assertThat(result.get(0).getPrice(), is(150.2)),
                () -> assertThat(result.get(0).getQuantityAvailable(), is(2)),
                () -> assertThat(result.get(0).getCategory(), is(categoryOfBookSet))
                );
    }

    @Test
    @DisplayName("Should not found a list of book by category")
    void shouldNotListAllBooksByCategory() {
        CategoryOfBook category = new CategoryOfBook(1L,"action");
        Set<CategoryOfBook> categoryOfBookSet = new HashSet<>();
        categoryOfBookSet.add(category);

        List<Book> bookList = new ArrayList<>();
        bookList.add(createBook().category(categoryOfBookSet).build());
        String categoryName = "categoryTest";

        Assertions.assertThrows(BookNotFoundException.class, () -> listAllBooksByCategoryImpl.listAllBooksByCategory(categoryName));
    }
}
