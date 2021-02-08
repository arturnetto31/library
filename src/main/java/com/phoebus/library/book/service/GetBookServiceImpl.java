package com.phoebus.library.book.service;

import com.phoebus.library.book.BookDTO;
import com.phoebus.library.book.BookRepository;
import com.phoebus.library.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBookServiceImpl implements GetBookService{
    private final BookRepository repository;

    @Override
    public BookDTO getBookDTO(Long id) {
        return BookDTO.from(repository.findById(id).orElseThrow(BookNotFoundException::new));
    }
}
