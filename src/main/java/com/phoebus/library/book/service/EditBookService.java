package com.phoebus.library.book.service;

import com.phoebus.library.book.BookDTO;

@FunctionalInterface
public interface EditBookService {
    void editBook(Long id, BookDTO bookDTO);
}
