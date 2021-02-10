package com.phoebus.library.categoryofbook.service;

import com.phoebus.library.categoryofbook.CategoryOfBookDTO;
import com.phoebus.library.categoryofbook.CategoryOfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ListPageCategoryOfBookServiceImpl implements ListPageCategoryOfBookService {
    private final CategoryOfBookRepository repository;
    @Override
    public Page<CategoryOfBookDTO> listPageOfCategoryOfBook(Pageable pageable) {
        return CategoryOfBookDTO.from(repository.findAll(pageable));
    }
}
