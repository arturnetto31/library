package com.phoebus.library.book.service;

import com.phoebus.library.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeleteBookServiceImpl implements DeleteBookService{
    private final BookRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
