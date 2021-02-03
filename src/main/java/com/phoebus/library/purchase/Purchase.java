package com.phoebus.library.purchase;


import com.phoebus.library.book.Book;
import com.phoebus.library.userlibrary.UserLibrary;
import lombok.*;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TB_PURCHASE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PURCHASE_ID")
    private Long id;

    @ManyToOne
    @JoinTable(name="USER_ID")
    private UserLibrary customer;

    @ManyToMany
    @PrimaryKeyJoinColumn
    private List<Book> shoppingList;

    private double priceToPay;
    private String purchaseDate;
    private boolean purchaseIsCompleted;

    public static Purchase to(PurchaseDTO purchaseDTO) {
        return Purchase.builder()
                .id(purchaseDTO.getId())
                .customer(purchaseDTO.getCustomer())
                .shoppingList(purchaseDTO.getShoppingList())
                .priceToPay(purchaseDTO.getPriceToPay())
                .purchaseDate(purchaseDTO.getPurchaseDate())
                .purchaseIsCompleted(purchaseDTO.isPurchaseCompleted())
                .build();
    }

    public static List<Purchase> to(List<PurchaseDTO> purchaseDTOS) {
        List<Purchase> listPurchase = new ArrayList<>();
        for(PurchaseDTO purchaseDTO : purchaseDTOS) {
            listPurchase.add(Purchase.to(purchaseDTO));
        }
        return listPurchase;
    }

    public static Page<Purchase> to(Page<PurchaseDTO> pages) {
        return pages.map(Purchase::to);
    }
}
