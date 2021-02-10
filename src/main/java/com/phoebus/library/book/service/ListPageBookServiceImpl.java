package com.phoebus.library.book.service;

import com.phoebus.library.book.BookDTO;
import com.phoebus.library.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListPageBookServiceImpl implements ListPageBookService{

    private final BookRepository repository;

    @Override
    public Page<BookDTO> listBookOnPage(Pageable pageable) {
        return BookDTO.from(repository.findAll(pageable));

    }
}
