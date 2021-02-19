package com.phoebus.library.book;


import com.phoebus.library.book.service.SaveBookServiceImpl;
import com.phoebus.library.categoryofbook.CategoryOfBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.phoebus.library.book.builders.BookBuilder.createBook;
import static com.phoebus.library.book.builders.BookBuilderDTO.createBookDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Test to verify if could save a book")
public class SaveBookServiceTest {
    @Mock
    private BookRepository repository;

    private SaveBookServiceImpl saveBookServiceImpl;

    @BeforeEach
    void setUp() {
        this.saveBookServiceImpl = new SaveBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Should save a book")
    void shouldSaveBook() {
        List<Book> allBooks = new ArrayList<>();

        CategoryOfBook category = new CategoryOfBook(1L,"action");
        Set<CategoryOfBook> categoryOfBookSet = new HashSet<>();
        categoryOfBookSet.add(category);

        Book book = createBook().isbn("0").category(categoryOfBookSet).build();
        BookDTO bookDTO = createBookDTO().category(categoryOfBookSet).build();
        allBooks.add(book);

        when(repository.findAll()).thenReturn(allBooks);

        saveBookServiceImpl.save(bookDTO);

        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(repository,times(1)).save(captorBook.capture());

        Book result = captorBook.getValue();

        assertAll("Book",
                () -> assertThat(result.getTitle(), is("teste book")),
                () -> assertThat(result.getSynopsis(), is("test")),
                () -> assertThat(result.getIsbn(), is("0000")),
                () -> assertThat(result.getAuthor(), is("teste")),
                () -> assertThat(result.getPrice(), is(150.2)),
                () -> assertThat(result.getQuantityAvailable(), is(2)),
                () -> assertThat(result.getCategory(), is(bookDTO.getCategory()))

                );

    }
}

