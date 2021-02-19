package com.phoebus.library.book.service;

import com.phoebus.library.book.BookDTO;
import com.phoebus.library.book.BookRepository;
import com.phoebus.library.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListAllBooksByCategoryImpl implements ListAllBooksByCategory {

    private final BookRepository repository;

    @Override
    public List<BookDTO> listAllBooksByCategory(String category) {

        if((repository.findBookByCategoryName(category)).isEmpty()){
            throw new BookNotFoundException();
        }
        return(BookDTO.from(repository.findBookByCategoryName(category)));
    }
}
