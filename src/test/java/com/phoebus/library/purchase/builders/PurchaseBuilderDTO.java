package com.phoebus.library.purchase.builders;

import com.phoebus.library.book.Book;
import com.phoebus.library.purchase.PurchaseDTO;
import com.phoebus.library.userlibrary.UserLibrary;

import java.util.Arrays;
import java.util.List;

import static com.phoebus.library.book.builders.BookBuilder.createBook;
import static com.phoebus.library.userlibrary.builders.UserLibraryBuilder.createUserLibrary;

public class PurchaseBuilderDTO {
    public static PurchaseDTO.Builder createPurchaseDTO() {
        UserLibrary userLibrary = createUserLibrary().build();
        List<Book> bookList = Arrays.asList(createBook().build());

        return PurchaseDTO.builder()
                .id(1L)
                .customer(userLibrary)
                .shoppingList(bookList)
                .purchaseDate("19-02-2021")
                .priceToPay(150.2)
                .purchaseCompleted(true);
    }

}
