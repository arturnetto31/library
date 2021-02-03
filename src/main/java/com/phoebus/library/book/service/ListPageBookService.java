package com.phoebus.library.book.service;


import com.phoebus.library.book.BookDTO;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface ListPageBookService {
    Page<BookDTO> listBookOnPage(Integer page, Integer size);
}
