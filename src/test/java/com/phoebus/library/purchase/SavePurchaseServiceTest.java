package com.phoebus.library.purchase;

import com.phoebus.library.book.Book;
import com.phoebus.library.purchase.service.SavePurchaseServiceImpl;
import com.phoebus.library.userlibrary.UserLibrary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify save purchase service")
public class SavePurchaseServiceTest {
    @Mock
    private PurchaseRepository repository;

    private SavePurchaseServiceImpl savePurchaseServiceImpl;

    @BeforeEach
    void setUp() {
        this.savePurchaseServiceImpl = new SavePurchaseServiceImpl(repository);
    }

    @Test
    @DisplayName("Should save a purchase")
    void shouldSavePurchase() {
        UserLibrary userLibrary = createUserLibrary().build();
        List<Book> bookList = Arrays.asList(createBook().build());
        Purchase purchase = createPurchase().shoppingList(bookList).customer(userLibrary).build();

        savePurchaseServiceImpl.save(PurchaseDTO.from(purchase));

        ArgumentCaptor<Purchase> captorPurchase = ArgumentCaptor.forClass(Purchase.class);
        verify(repository, times(1)).save(captorPurchase.capture());

        Purchase result = captorPurchase.getValue();

        assertAll("Purchase",
                () -> assertThat(result.getCustomer(), is(userLibrary)),
                () -> assertThat(result.getShoppingList(),is(bookList)),
                () -> assertThat(result.getPurchaseDate(), is("19-02-2021")),
                () -> assertThat(result.getPriceToPay(), is(150.2)),
                () -> assertThat(result.isPurchaseIsCompleted(),is(true))
                );
    }
}
