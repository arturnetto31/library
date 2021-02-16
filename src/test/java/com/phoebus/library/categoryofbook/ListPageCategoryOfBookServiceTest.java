package com.phoebus.library.categoryofbook;

import com.phoebus.library.categoryofbook.service.ListPageCategoryOfBookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static com.phoebus.library.categoryofbook.builders.CategoryOfBookBuilder.createCategoryOfBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify a List page category of book service")
public class ListPageCategoryOfBookServiceTest {

    @Mock
    private CategoryOfBookRepository repository;

    private ListPageCategoryOfBookServiceImpl listPageCategoryOfBookService;

    @BeforeEach
    void setUp() {
        this.listPageCategoryOfBookService = new ListPageCategoryOfBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Test to verify if could get a list page category when successful")
    void shouldGetListPageCategory() {
        Pageable pageable = PageRequest.of(0,2);

        when(repository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.nCopies(2, createCategoryOfBook().build())));

        Page<CategoryOfBookDTO> result = listPageCategoryOfBookService.listPageOfCategoryOfBook(pageable);

        assertAll("Category of Book",
                () -> assertThat(result.getNumber(), is(0)),
                () -> assertThat(result.getTotalElements(), is(2L)),
                () -> assertThat(result.getTotalPages(), is(1)),
                () -> assertThat(result.getSize(), is(2)),

                () -> assertThat(result.getContent().get(0).getName(), is("action")),

                () -> assertThat(result.getContent().get(1).getName(), is("action"))
                );

    }
}
