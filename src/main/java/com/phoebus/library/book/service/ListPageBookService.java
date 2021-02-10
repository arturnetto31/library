package com.phoebus.library.book.service;

import com.phoebus.library.book.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@FunctionalInterface
public interface ListPageBookService {
    Page<BookDTO> listBookOnPage(Pageable pageable);
}
