package com.phoebus.library.book.v1;


import com.phoebus.library.book.Book;
import com.phoebus.library.book.BookDTO;
import com.phoebus.library.book.service.DeleteBookService;
import com.phoebus.library.book.service.EditBookService;
import com.phoebus.library.book.service.GetBookService;
import com.phoebus.library.book.service.ListBookService;
import com.phoebus.library.book.service.SaveBookService;
import com.phoebus.library.book.service.ListPageBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/books")
public class BookControllerV1 {
    private final DeleteBookService deleteBookService;
    private final EditBookService editBookService;
    private final GetBookService getBookService;
    private final ListBookService listBookService;
    private final SaveBookService saveBookService;
    private final ListPageBookService listPageBookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBook(@RequestBody @Valid Book newBook) {
        saveBookService.save(newBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable(value = "id") Long id) {
        deleteBookService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editBook(@PathVariable(value = "id")Long id, @RequestBody BookDTO bookDTO) {
        editBookService.editBook(id,bookDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO bookById(@PathVariable(value = "id") Long id) {
        return getBookService.getBookDTO(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> listOfBooks() {
        return listBookService.listBooks();
    }

    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Page<BookDTO> findPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return listPageBookService.listBookOnPage(page,size);
    }
}
