package com.phoebus.library.book;

import com.phoebus.library.categoryofbook.CategoryOfBook;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class BookDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Title may not be empty")
    private String title;
    @NotEmpty(message = "Synopsis may not be empty")
    private String synopsis;
    @NotEmpty(message = "ISBN may not be empty")
    private String isbn;
    @NotEmpty(message = "Author may not be empty")
    private String author;
    @NotEmpty(message = "Price may not be empty")
    private double price;
    @NotEmpty(message = "Quantity Available may not be empty")
    private int quantityAvailable;
    @NotEmpty(message = "Category may not be empty")
    private List<CategoryOfBook> category;

    public static BookDTO from(Book book){
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .synopsis(book.getSynopsis())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .price(book.getPrice())
                .category(book.getCategory())
                .build();
    }
    public static List<BookDTO> from(List<Book> books) {
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book : books) {
            bookDTOS.add(BookDTO.from(book));
        }
        return bookDTOS;
    }
}