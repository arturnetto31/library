package com.phoebus.library.purchase;


import com.phoebus.library.book.Book;
import com.phoebus.library.userlibrary.UserLibrary;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Customer may not be empty")
    private UserLibrary customer;
    @NotEmpty(message = "Shopping list may not be empty")
    private List<Book> shoppingList;
    @NotEmpty(message = "Price to pay may not be empty")
    private double priceToPay;
    @NotEmpty(message = "Purchase date may not be empty")
    private String purchaseDate;
    @NotEmpty(message = "Purchase Completed may not be empty")
    private boolean purchaseCompleted;

    public static PurchaseDTO from(Purchase purchase) {
        return PurchaseDTO.builder()
                .id(purchase.getId())
                .customer(purchase.getCustomer())
                .purchaseDate(purchase.getPurchaseDate())
                .priceToPay(purchase.getPriceToPay())
                .shoppingList(purchase.getShoppingList())
                .purchaseCompleted(purchase.isPurchaseIsCompleted())
                .build();
    }
    public static List<PurchaseDTO> from(List<Purchase> listPurchases) {
        List<PurchaseDTO> listPurchaseDTOS = new ArrayList<>();
        for (Purchase purchase : listPurchases) {
            listPurchaseDTOS.add(PurchaseDTO.from(purchase));
        }
        return listPurchaseDTOS;
    }
}
