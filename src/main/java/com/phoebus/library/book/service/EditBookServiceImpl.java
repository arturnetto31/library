package com.phoebus.library.book.service;

import com.phoebus.library.book.Book;
import com.phoebus.library.book.BookDTO;
import com.phoebus.library.book.BookRepository;
import com.phoebus.library.exceptions.BookInconsistencyInDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditBookServiceImpl implements EditBookService{
    private final BookRepository repository;

    @Override
    public void editBook(Long id, BookDTO bookDTO) {
        Book book = repository.findById(id).orElseThrow(BookInconsistencyInDataException::new);

        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setSynopsis(bookDTO.getSynopsis());
        book.setPrice(bookDTO.getPrice());
        book.setTitle(bookDTO.getTitle());
        book.setQuantityAvailable(bookDTO.getQuantityAvailable());

        repository.save(book);
    }
}
