package com.phoebus.library.categoryofbook.service;

import com.phoebus.library.categoryofbook.CategoryOfBookDTO;

@FunctionalInterface
public interface SaveCategoryOfBook {
    void saveCategoryOfBook(CategoryOfBookDTO categoryOfBookDTO);
}
