package com.phoebus.library.categoryofbook.service;

import com.phoebus.library.categoryofbook.CategoryOfBook;
import com.phoebus.library.categoryofbook.CategoryOfBookDTO;
import com.phoebus.library.categoryofbook.CategoryOfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveCategoryOfBookImpl implements SaveCategoryOfBook{
    private final CategoryOfBookRepository repository;
    @Override
    public void saveCategoryOfBook(CategoryOfBookDTO categoryOfBookDTO) {
        repository.save(CategoryOfBook.to(categoryOfBookDTO));
    }
}
