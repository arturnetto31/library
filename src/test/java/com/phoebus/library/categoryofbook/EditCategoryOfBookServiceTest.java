package com.phoebus.library.categoryofbook;

import com.phoebus.library.categoryofbook.service.EditCategoryOfBookServiceImpl;
import com.phoebus.library.exceptions.CategoryOfBookInconsistencyInDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.phoebus.library.categoryofbook.builders.CategoryOfBookBuilder.createCategoryOfBook;
import static com.phoebus.library.categoryofbook.builders.CategoryOfBookBuilderDTO.createCategoryOfBookDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify a test of edit category of book")
public class EditCategoryOfBookServiceTest {
    @Mock
    private CategoryOfBookRepository repository;

    private EditCategoryOfBookServiceImpl editCategoryOfBookServiceImpl;

    @BeforeEach
    void setUp() {
        this.editCategoryOfBookServiceImpl = new EditCategoryOfBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Test to verify if could Edit a category of book when successful")
    void shouldEditCategoryOfBook() {
        CategoryOfBookDTO categoryOfBookDTO = createCategoryOfBookDTO().name("romance").build();
        Optional<CategoryOfBook> categoryOfBookOptional = Optional.of(createCategoryOfBook().build());
        when(repository.findById(anyLong())).thenReturn(categoryOfBookOptional);

        editCategoryOfBookServiceImpl.editCategoryOfBook(categoryOfBookDTO, 1L);

        ArgumentCaptor<CategoryOfBook> captorCategoryOfBook = ArgumentCaptor.forClass(CategoryOfBook.class);
        verify(repository, times(1)).save(captorCategoryOfBook.capture());

        CategoryOfBook result = captorCategoryOfBook.getValue();

        assertAll("CategoryOfBook",
                () -> assertThat(result.getName(), is("romance")));
    }

    @Test
    @DisplayName("Should not edit a category of book")
    void shouldNotEditCategoryOfBook() {
        CategoryOfBookDTO categoryOfBookDTO = createCategoryOfBookDTO().name("romance").build();
        when(repository.findById(anyLong())).thenThrow(new CategoryOfBookInconsistencyInDataException());

        assertThrows(CategoryOfBookInconsistencyInDataException.class, () -> editCategoryOfBookServiceImpl.editCategoryOfBook(categoryOfBookDTO, 1L));

        verify(repository,times(0)).save(any());
    }
}
