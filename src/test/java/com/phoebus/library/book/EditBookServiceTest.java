package com.phoebus.library.book;


import com.phoebus.library.book.service.EditBookServiceImpl;
import com.phoebus.library.exceptions.BookInconsistencyInDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.phoebus.library.book.builders.BookBuilder.createBook;
import static com.phoebus.library.book.builders.BookBuilderDTO.createBookDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Test to verify if could edit a book")
public class EditBookServiceTest {
    @Mock
    private BookRepository repository;

    private EditBookServiceImpl editBookServiceImpl;

    @BeforeEach
    void setUp() {
        this.editBookServiceImpl = new EditBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Should edit a book")
    void shouldEditBook() {
        BookDTO bookDTO = createBookDTO().title("test book").build();
        Book book = createBook().build();

        Optional<Book> bookOptional = Optional.of(book);
        when(repository.findById(anyLong())).thenReturn(bookOptional);

        editBookServiceImpl.editBook(1L,bookDTO);

        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(repository,times(1)).save(captorBook.capture());

        Book result = captorBook.getValue();

        assertAll("Book",
                () -> assertThat(result.getTitle(), is("test book")),
                () -> assertThat(result.getAuthor(), is("teste")),
                () -> assertThat(result.getIsbn(),is("0000")),
                () -> assertThat(result.getAuthor(), is("teste")),
                () -> assertThat(result.getPrice(), is(150.2)),
                () -> assertThat(result.getQuantityAvailable(), is(2)),
                () -> assertThat(result.getSynopsis(), is("test")),
                () -> assertThat(result.getCategory(), is(book.getCategory()))
                );
    }
    @Test
    @DisplayName("Should not edit a book")
    void shouldNotEditBook() {
        BookDTO bookDTO = createBookDTO().title("test book").build();
        when(repository.findById(anyLong())).thenThrow(new BookInconsistencyInDataException());

        assertThrows(BookInconsistencyInDataException.class, () -> editBookServiceImpl.editBook(1L,bookDTO));

        verify(repository, times(0)).save(any());
    }
}
