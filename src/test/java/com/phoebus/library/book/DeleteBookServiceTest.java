package com.phoebus.library.book;


import com.phoebus.library.book.service.DeleteBookServiceImpl;
import com.phoebus.library.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Test to try delete a book")
public class DeleteBookServiceTest {
    @Mock
    private BookRepository repository;

    private DeleteBookServiceImpl deleteBookServiceImpl;

    @BeforeEach
    void setUp() {
        this.deleteBookServiceImpl = new DeleteBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Should delete a Book")
    public void shouldDeleteBook() {
        when(repository.existsById(anyLong())).thenReturn(true);
        deleteBookServiceImpl.delete(1L);
        verify(repository).deleteById(anyLong());
    }

    @Test
    @DisplayName("Should not delete a book")
    public void shouldNotDeleteBook() {
        when(repository.existsById(anyLong())).thenReturn(false);

        Assertions.assertThrows(BookNotFoundException.class, () -> deleteBookServiceImpl.delete(1L));

        verify(repository,times(0)).deleteById(anyLong());
    }
}
