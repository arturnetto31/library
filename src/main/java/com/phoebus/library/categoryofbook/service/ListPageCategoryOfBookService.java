package com.phoebus.library.categoryofbook.service;

import com.phoebus.library.categoryofbook.CategoryOfBookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPageCategoryOfBookService {
    Page<CategoryOfBookDTO> listPageOfCategoryOfBook(Pageable pageable);
}
