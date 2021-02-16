package com.phoebus.library.categoryofbook;


import com.phoebus.library.categoryofbook.service.DeleteCategoryOfBookServiceImpl;
import com.phoebus.library.exceptions.CategoryOfBookNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify a delete service")
public class DeleteCategoryOfBookServiceTest {

    @Mock
    private CategoryOfBookRepository repository;

    private DeleteCategoryOfBookServiceImpl deleteCategoryOfBookServiceImpl;

    @BeforeEach
    void setUp() {
        this.deleteCategoryOfBookServiceImpl = new DeleteCategoryOfBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Test to verify if could delete a category of book when successful")
    void shouldDeleteCategoryOfBook() {
        when(repository.existsById(anyLong())).thenReturn(true);
        deleteCategoryOfBookServiceImpl.delete(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Test to verify if could delete a category of book when failed")
    void shouldNotDeleteCategoryOfBook() {
        when(repository.existsById(anyLong())).thenReturn(false);
        Assertions.assertThrows(CategoryOfBookNotFoundException.class, () -> deleteCategoryOfBookServiceImpl.delete(1L));
        verify(repository, times(0)).deleteById(anyLong());
    }
}
