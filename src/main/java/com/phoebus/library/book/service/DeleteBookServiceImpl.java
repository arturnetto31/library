package com.phoebus.library.book.service;

import com.phoebus.library.book.BookRepository;
import com.phoebus.library.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeleteBookServiceImpl implements DeleteBookService{
    private final BookRepository repository;

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new BookNotFoundException();
        }
        repository.deleteById(id);
    }
}
