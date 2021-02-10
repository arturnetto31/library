package com.phoebus.library.book.service;

import com.phoebus.library.book.Book;
import com.phoebus.library.book.BookDTO;
import com.phoebus.library.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveBookServiceImpl implements SaveBookService {
    private final BookRepository repository;

    @Override
    public void save(BookDTO bookDTO) {
        repository.save(Book.to(bookDTO));
    }
}
