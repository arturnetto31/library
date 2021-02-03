package com.phoebus.library.book.service;


import com.phoebus.library.book.BookDTO;
import com.phoebus.library.book.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListPageBookServiceImpl implements ListPageBookService{

    public BookRepository repository;

    @Override
    public Page<BookDTO> listBookOnPage(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");

        return BookDTO.from(repository.findAll(pageRequest));

    }
}
