package com.phoebus.library.book.builders;

import com.phoebus.library.book.Book;
import com.phoebus.library.categoryofbook.CategoryOfBook;

import java.util.HashSet;
import java.util.Set;

public class BookBuilder {
    public static Book.Builder createBook() {
        CategoryOfBook category = new CategoryOfBook(1L,"action");
        Set<CategoryOfBook> categoryOfBookSet = new HashSet<>();
        categoryOfBookSet.add(category);

        return Book.builder()
                .id(1L)
                .title("teste book")
                .synopsis("test")
                .isbn("0000")
                .author("teste")
                .price(150.2)
                .quantityAvailable(2)
                .category(categoryOfBookSet);
    }
}
