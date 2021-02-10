package com.phoebus.library.categoryofbook.service;

import com.phoebus.library.categoryofbook.CategoryOfBookDTO;
import java.util.List;

@FunctionalInterface
public interface ListCategoryOfBookService {
    List<CategoryOfBookDTO> listAllCategoriesOfBook();
}
