package com.phoebus.library.book.builders;

import com.phoebus.library.book.Book;
import com.phoebus.library.categoryofbook.CategoryOfBook;

import java.util.ArrayList;
import java.util.List;

public class BookBuilder {
    public static Book.Builder createBook() {
        List<CategoryOfBook> category = new ArrayList<>();
        category.add(new CategoryOfBook(1L,"categoryTest"));
        return Book.builder()
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
