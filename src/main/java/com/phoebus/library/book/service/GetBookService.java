package com.phoebus.library.book.service;


import com.phoebus.library.book.BookDTO;

@FunctionalInterface
public interface GetBookService {
    public BookDTO getBookDTO(Long id);
}
