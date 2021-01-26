package com.phoebus.library.book.service;


import com.phoebus.library.book.Book;
import com.phoebus.library.book.BookDTO;
import com.phoebus.library.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListBookServiceImpl implements ListBookService {
    private final BookRepository repository;

    @Override
    public List<BookDTO> listBooks() {
        List<Book> books = repository.findAll();
        return BookDTO.from(books);
    }
}
