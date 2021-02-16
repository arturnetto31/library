package com.phoebus.library.categoryofbook;

import com.phoebus.library.categoryofbook.service.SaveCategoryOfBookImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.phoebus.library.categoryofbook.builders.CategoryOfBookBuilder.createCategoryOfBook;
import static com.phoebus.library.categoryofbook.builders.CategoryOfBookBuilderDTO.createCategoryOfBookDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify save category of book service")
public class SaveCategoryOfBookServiceTest {
    @Mock
    private CategoryOfBookRepository repository;

    private SaveCategoryOfBookImpl saveCategoryOfBookImpl;

    @BeforeEach
    void setUp() {
        this.saveCategoryOfBookImpl = new SaveCategoryOfBookImpl(repository);
    }

    @Test
    @DisplayName("Test to verify if could save a category of book when successful")
    void shouldSaveCategoryBook() {
        CategoryOfBookDTO categoryOfBookDTO = createCategoryOfBookDTO().build();

        saveCategoryOfBookImpl.saveCategoryOfBook(categoryOfBookDTO);

        ArgumentCaptor<CategoryOfBook> captorCategoryOfBook = ArgumentCaptor.forClass(CategoryOfBook.class);
        verify(repository,times(1)).save(captorCategoryOfBook.capture());

        CategoryOfBook result = captorCategoryOfBook.getValue();

        assertAll("CategoryOfBook",
                () -> assertThat(result.getName(), is("action"))
                );
    }
}
