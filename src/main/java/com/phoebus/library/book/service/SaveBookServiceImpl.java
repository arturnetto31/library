package com.phoebus.library.book.service;

import com.phoebus.library.book.Book;
import com.phoebus.library.book.BookDTO;
import com.phoebus.library.book.BookRepository;
import com.phoebus.library.exceptions.BookAlreadyExists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveBookServiceImpl implements SaveBookService {
    private final BookRepository repository;

    @Override
    public void save(BookDTO bookDTO) {
        List<Book> allBooks = repository.findAll();
        for(Book book: allBooks){
            if(book.getIsbn().equals(bookDTO.getIsbn())){
                throw new BookAlreadyExists();
            }
        }
        repository.save(Book.to(bookDTO));

    }
}
