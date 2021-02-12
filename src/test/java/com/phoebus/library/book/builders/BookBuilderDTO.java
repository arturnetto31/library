package com.phoebus.library.book.builders;

import com.phoebus.library.book.BookDTO;
import com.phoebus.library.categoryofbook.CategoryOfBook;

import java.util.ArrayList;
import java.util.List;

public class BookBuilderDTO {
    public static BookDTO.Builder createBookDTO() {
        List<CategoryOfBook> category = new ArrayList<>();
        category.add(new CategoryOfBook(1L,"categoryTest"));

        return BookDTO.builder()
                .id(1L)
                .title("teste book")
                .synopsis("test")
                .isbn("0000")
                .author("teste")
                .price(150.2)
                .quantityAvailable(2)
                .category(category);
    }
}
