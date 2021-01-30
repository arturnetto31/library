package com.phoebus.library.book;


import com.phoebus.library.categoryofbook.CategoryOfBook;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TB_BOOK")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BOOK_ID")
    private Long id;

    private String title;
    private String synopsis;

    @Column(unique = true)
    private String isbn;

    private String author;
    private double price;
    private int quantityAvailable;
    @OneToMany
    @PrimaryKeyJoinColumn
    private List<CategoryOfBook> category;

    public static Book to(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .synopsis(bookDTO.getSynopsis())
                .isbn(bookDTO.getIsbn())
                .author(bookDTO.getAuthor())
                .price(bookDTO.getPrice())
                .quantityAvailable(bookDTO.getQuantityAvailable())
                .category(bookDTO.getCategory())
                .build();
    }

    public static List<Book> to(List<BookDTO> bookDTOS) {
        List<Book> listBook = new ArrayList<>();
        for(BookDTO bookDTO : bookDTOS) {
            listBook.add(Book.to(bookDTO));
        }

        return listBook;
    }
}
