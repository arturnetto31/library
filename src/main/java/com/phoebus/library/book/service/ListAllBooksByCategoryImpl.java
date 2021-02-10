package com.phoebus.library.book.service;

import com.phoebus.library.book.Book;
import com.phoebus.library.book.BookDTO;
import com.phoebus.library.book.BookRepository;
import com.phoebus.library.categoryofbook.CategoryOfBook;
import com.phoebus.library.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ListAllBooksByCategoryImpl implements ListAllBooksByCategory{

    private final BookRepository repository;

    @Override
    public List<BookDTO> listAllBooksByCategory(String category) {
        List<Book> allBooks = repository.findAll();
        List<Book> bookByCategory = new ArrayList<>();

        for(Book book : allBooks) {
            for(CategoryOfBook categoryOfBook: book.getCategory()) {
                if(categoryOfBook.getName().equals(category)){
                    bookByCategory.add(book);
                }
            }
        }
        if(bookByCategory.isEmpty()){
            throw new BookNotFoundException();
        }
        return BookDTO.from(bookByCategory);
    }
}
