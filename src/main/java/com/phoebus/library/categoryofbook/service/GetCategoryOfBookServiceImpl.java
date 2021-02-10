package com.phoebus.library.categoryofbook.service;

import com.phoebus.library.categoryofbook.CategoryOfBookDTO;
import com.phoebus.library.categoryofbook.CategoryOfBookRepository;
import com.phoebus.library.exceptions.CategoryOfBookNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCategoryOfBookServiceImpl implements GetCategoryOfBookService{

    private final CategoryOfBookRepository repository;

    @Override
    public CategoryOfBookDTO getCategoryOfBook(Long id) {
        return CategoryOfBookDTO.from(repository.findById(id).orElseThrow(CategoryOfBookNotFound::new));
    }
}
