package com.phoebus.library.book;


import com.phoebus.library.categoryofbook.CategoryOfBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="TB_BOOK")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BOOK_ID")
    private Long id;

    private String title;
    private String synopsis;
    private String isbn;
    private String author;
    private double price;
    private int quantityAvailable;
    @OneToMany
    @PrimaryKeyJoinColumn
    private List<CategoryOfBook> category;
}
