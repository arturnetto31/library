package com.phoebus.library.book;

import com.phoebus.library.categoryofbook.CategoryOfBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class BookDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Title may not be empty")
    @Size(min = 2)
    private String title;
    @NotEmpty(message = "Synopsis may not be empty")
    @Size(min = 3)
    private String synopsis;
    @NotEmpty(message = "ISBN may not be empty")
    @Size(min = 3)
    private String isbn;
    @NotEmpty(message = "Author may not be empty")
    @Size(min = 3)
    private String author;
    @NotNull
    @Min(0)
    private double price;
    @NotNull
    @Min(0)
    private int quantityAvailable;
    @NotEmpty(message = "Category may not be empty")
    private Set<CategoryOfBook> category;

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