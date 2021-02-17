package com.phoebus.library.purchase;

import com.phoebus.library.book.Book;
import com.phoebus.library.userlibrary.UserLibrary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private UserLibrary customer;
    @NotEmpty(message = "Shopping list may not be empty")
    private List<Book> shoppingList;
    @NotNull
    @Min(0)
    private double priceToPay;
    @NotEmpty(message = "Purchase date may not be empty")
    private String purchaseDate;
    @NotNull
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

    public static Page<PurchaseDTO> from(Page<Purchase> pages) {
        return pages.map(PurchaseDTO::from);
    }
}
