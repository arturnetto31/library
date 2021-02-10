package com.phoebus.library.categoryofbook.service;

import com.phoebus.library.categoryofbook.CategoryOfBookRepository;
import com.phoebus.library.exceptions.CategoryOfBookNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteCategoryOfBookServiceImpl implements DeleteCategoryOfBookService{

    private final CategoryOfBookRepository repository;

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new CategoryOfBookNotFound();
        }
        repository.deleteById(id);
    }
}
