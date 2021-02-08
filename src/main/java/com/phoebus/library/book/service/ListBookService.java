package com.phoebus.library.book.service;

import com.phoebus.library.book.BookDTO;

import java.util.List;

@FunctionalInterface
public interface ListBookService {
    List<BookDTO> listBooks();
}
