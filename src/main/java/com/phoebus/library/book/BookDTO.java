package com.phoebus.library.book;

import com.phoebus.library.categoryofbook.CategoryOfBook;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private double price;
    @NotNull
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
                .quantityAvailable(book.getQuantityAvailable())
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

    public static Page<BookDTO> from(Page<Book> pages) {
        return pages.map(BookDTO::from);
    }
}