package com.phoebus.library.purchase;

import com.phoebus.library.book.Book;
import com.phoebus.library.purchase.service.ListPurchaseServiceImpl;
import com.phoebus.library.userlibrary.UserLibrary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static com.phoebus.library.book.builders.BookBuilder.createBook;
import static com.phoebus.library.purchase.builders.PurchaseBuilder.createPurchase;
import static com.phoebus.library.userlibrary.builders.UserLibraryBuilder.createUserLibrary;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify list purchase service")
public class ListPurchasesServiceTest {

    @Mock
    private PurchaseRepository repository;

    private ListPurchaseServiceImpl listPurchaseServiceImpl;

    @BeforeEach
    void setUp() {
        this.listPurchaseServiceImpl = new ListPurchaseServiceImpl(repository);
    }

    @Test
    @DisplayName("Should list a purchase")
    void shouldListPurchase() {
        UserLibrary userLibrary = createUserLibrary().build();
        List<Book> bookList = Arrays.asList(createBook().build());
        List<Purchase> purchaseList = Arrays.asList(createPurchase().customer(userLibrary).shoppingList(bookList).build());

        when(repository.findAll()).thenReturn(purchaseList);
        List<PurchaseDTO> result = listPurchaseServiceImpl.listPurchases();

        assertAll("Purchase",
                () -> assertThat(result.size(), is(1)),
                () -> assertThat(result.get(0).getCustomer(), is(userLibrary)),
                () -> assertThat(result.get(0).getShoppingList(), is(bookList)),
                () -> assertThat(result.get(0).getPurchaseDate(), is("19-02-2021")),
                () -> assertThat(result.get(0).getPriceToPay(), is(150.2)),
                () -> assertThat(result.get(0).isPurchaseCompleted(), is(true))
                );


    }
}
