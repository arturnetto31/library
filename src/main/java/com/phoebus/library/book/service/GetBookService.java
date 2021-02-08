package com.phoebus.library.book.service;

import com.phoebus.library.book.BookDTO;

@FunctionalInterface
public interface GetBookService {
    BookDTO getBookDTO(Long id);
}
