package com.phoebus.library.book.service;

import com.phoebus.library.book.BookDTO;

@FunctionalInterface
public interface SaveBookService {
    void save(BookDTO bookDTO);
}
