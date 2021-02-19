package com.phoebus.library.purchase.builders;

import com.phoebus.library.book.Book;
import com.phoebus.library.purchase.Purchase;
import com.phoebus.library.userlibrary.UserLibrary;

import java.util.Arrays;
import java.util.List;

import static com.phoebus.library.book.builders.BookBuilder.createBook;
import static com.phoebus.library.userlibrary.builders.UserLibraryBuilder.createUserLibrary;

public class PurchaseBuilder {
    public static Purchase.Builder createPurchase() {
        UserLibrary userLibrary = createUserLibrary().build();
        List<Book> bookList = Arrays.asList(createBook().build());
        return Purchase.builder()
                .id(1L)
                .customer(userLibrary)
                .shoppingList(bookList)
                .purchaseDate("19-02-2021")
                .priceToPay(150.2)
                .purchaseIsCompleted(true);
    }
}
