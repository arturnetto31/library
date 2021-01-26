package com.phoebus.library.purchase;


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
    private Long id;

    private UserLibrary customer;
    private List<Long> shoppingList;
    private double priceToPay;
    private String purchaseDate;
    private boolean purchaseIsCompleted;
}
