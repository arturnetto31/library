package com.phoebus.library.categoryofbook.service;

import com.phoebus.library.categoryofbook.CategoryOfBookDTO;
import com.phoebus.library.categoryofbook.CategoryOfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListCategoryOfBookServiceImpl implements ListCategoryOfBookService{
    private final CategoryOfBookRepository repository;
    @Override
    public List<CategoryOfBookDTO> listAllCategoriesOfBook() {
        return CategoryOfBookDTO.from(repository.findAll());
    }
}
