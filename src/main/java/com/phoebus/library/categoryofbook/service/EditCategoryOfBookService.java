package com.phoebus.library.categoryofbook.service;

import com.phoebus.library.categoryofbook.CategoryOfBookDTO;

@FunctionalInterface
public interface EditCategoryOfBookService {
    void editCategoryOfBook(CategoryOfBookDTO categoryOfBookDTO, Long id);
}
