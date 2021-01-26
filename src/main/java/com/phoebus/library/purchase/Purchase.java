package com.phoebus.library.purchase;


import com.phoebus.library.book.Book;
import com.phoebus.library.userlibrary.UserLibrary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="TB_PURCHASE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    /*
    @JoinTable(name = "SELL_BOOK",
            joinColumns = @JoinColumn(name = "PURCHASE_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
     */
    @PrimaryKeyJoinColumn
    private List<Book> shoppingList;

    private double priceToPay;
    private String purchaseDate;
    private boolean purchaseIsCompleted;
}
