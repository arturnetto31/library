package com.phoebus.library.categoryofbook.service;

import com.phoebus.library.categoryofbook.CategoryOfBook;
import com.phoebus.library.categoryofbook.CategoryOfBookDTO;
import com.phoebus.library.categoryofbook.CategoryOfBookRepository;
import com.phoebus.library.exceptions.CategoryOfBookInconsistencyInDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditCategoryOfBookServiceImpl implements EditCategoryOfBookService{
    private final CategoryOfBookRepository repository;

    @Override
    public void editCategoryOfBook(CategoryOfBookDTO categoryOfBookDTO, Long id) {
        CategoryOfBook attCategoryOfBook = repository.findById(id).orElseThrow(CategoryOfBookInconsistencyInDataException :: new);

        attCategoryOfBook.setName(categoryOfBookDTO.getName());

        repository.save(attCategoryOfBook);
    }
}
