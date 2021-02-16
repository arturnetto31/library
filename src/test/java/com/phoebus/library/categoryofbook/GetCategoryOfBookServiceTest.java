package com.phoebus.library.categoryofbook;

import com.phoebus.library.categoryofbook.service.GetCategoryOfBookServiceImpl;
import com.phoebus.library.exceptions.CategoryOfBookNotFoundException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.phoebus.library.categoryofbook.builders.CategoryOfBookBuilder.createCategoryOfBook;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify get category of book service")
public class GetCategoryOfBookServiceTest {
    @Mock
    private CategoryOfBookRepository repository;

    private GetCategoryOfBookServiceImpl getCategoryOfBookServiceImpl;

    @BeforeEach
    void setUp() {
        this.getCategoryOfBookServiceImpl = new GetCategoryOfBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Test to verify if could get a category of book by id when successful")
    void shouldGetCategoryOfBook() {
        Optional<CategoryOfBook> categoryOfBookOptional = Optional.of(createCategoryOfBook().build());

        when(repository.findById(anyLong())).thenReturn(categoryOfBookOptional);

        CategoryOfBookDTO result = this.getCategoryOfBookServiceImpl.getCategoryOfBook(1L);

        assertAll("Category of Book",
                () -> assertThat(result.getName(), is("action")));
    }

    @Test
    @DisplayName("Test to verify if could get a category of book by id when failed ")
    void shouldNotGetCategoryOfBook() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(CategoryOfBookNotFoundException.class, () -> getCategoryOfBookServiceImpl.getCategoryOfBook(1L));

        verify(repository).findById(anyLong());
    }

}
