package com.phoebus.library.categoryofbook.service;


import com.phoebus.library.categoryofbook.CategoryOfBookDTO;

@FunctionalInterface
public interface GetCategoryOfBookService {
    CategoryOfBookDTO getCategoryOfBook(Long id);
}
