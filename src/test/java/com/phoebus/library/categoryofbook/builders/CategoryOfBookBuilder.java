package com.phoebus.library.categoryofbook.builders;

import com.phoebus.library.categoryofbook.CategoryOfBook;

public class CategoryOfBookBuilder {
    public static CategoryOfBook.Builder createCategoryOfBook() {
        return CategoryOfBook.builder()
                .id(1L)
                .name("action");
    }
}
