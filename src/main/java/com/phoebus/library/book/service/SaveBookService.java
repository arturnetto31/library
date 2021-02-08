package com.phoebus.library.book.service;

import com.phoebus.library.book.Book;

@FunctionalInterface
public interface SaveBookService {
    public void save(Book book);
}
