package com.phoebus.library.purchase;

import com.phoebus.library.book.Book;
import com.phoebus.library.purchase.service.ListPagePurchaseServiceImpl;
import com.phoebus.library.userlibrary.UserLibrary;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.phoebus.library.book.builders.BookBuilder.createBook;
import static com.phoebus.library.purchase.builders.PurchaseBuilder.createPurchase;
import static com.phoebus.library.userlibrary.builders.UserLibraryBuilder.createUserLibrary;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify list page purchase service")
public class ListPagePurchaseServiceTest {
    @Mock
    private PurchaseRepository repository;

    private ListPagePurchaseServiceImpl listPagePurchaseServiceImpl;

    @BeforeEach
    void setUp(){
        this.listPagePurchaseServiceImpl = new ListPagePurchaseServiceImpl(repository);
    }

    @Test
    @DisplayName("Should list page purchase")
    void shouldListPagePurchase() {
        Pageable pageable = PageRequest.of(0,2);
        UserLibrary userLibrary = createUserLibrary().id(1L).build();
        List<Book> bookList = Arrays.asList(createBook().id(2L).build());

        when(repository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.nCopies(2,createPurchase().customer(userLibrary).shoppingList(bookList).build())));
        Page<PurchaseDTO> result = listPagePurchaseServiceImpl.listPurchaseOnPage(pageable);

        assertAll("Purchase",
                () -> assertThat(result.getNumber(), is(0)),
                () -> assertThat(result.getTotalElements(), is(2L)),
                () -> assertThat(result.getTotalPages(), is(1)),
                () -> assertThat(result.getSize(), is(2)),

                () -> assertThat(result.getContent().get(0).getCustomer(), is(userLibrary)),
                () -> assertThat(result.getContent().get(0).getShoppingList(), is(bookList)),
                () -> assertThat(result.getContent().get(0).getPurchaseDate(), is("19-02-2021")),
                () -> assertThat(result.getContent().get(0).getPriceToPay(), is(150.2)),
                () -> assertThat(result.getContent().get(0).isPurchaseCompleted(), is(true)),

                () -> assertThat(result.getContent().get(1).getCustomer(), is(userLibrary)),
                () -> assertThat(result.getContent().get(1).getShoppingList(), is(bookList)),
                () -> assertThat(result.getContent().get(1).getPurchaseDate(), is("19-02-2021")),
                () -> assertThat(result.getContent().get(1).getPriceToPay(), is(150.2)),
                () -> assertThat(result.getContent().get(1).isPurchaseCompleted(), is(true))

                );
    }

}
