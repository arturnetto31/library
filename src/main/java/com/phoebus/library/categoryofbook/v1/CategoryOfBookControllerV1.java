package com.phoebus.library.categoryofbook.v1;


import com.phoebus.library.categoryofbook.CategoryOfBookDTO;
import com.phoebus.library.categoryofbook.service.DeleteCategoryOfBookService;
import com.phoebus.library.categoryofbook.service.EditCategoryOfBookService;
import com.phoebus.library.categoryofbook.service.GetCategoryOfBookService;
import com.phoebus.library.categoryofbook.service.ListCategoryOfBookService;
import com.phoebus.library.categoryofbook.service.ListPageCategoryOfBookService;
import com.phoebus.library.categoryofbook.service.SaveCategoryOfBook;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/categorybook")
@RequiredArgsConstructor
public class CategoryOfBookControllerV1 {
    private final DeleteCategoryOfBookService deleteCategoryOfBookService;
    private final EditCategoryOfBookService editCategoryOfBookService;
    private final GetCategoryOfBookService getCategoryOfBookService;
    private final ListCategoryOfBookService listCategoryOfBookService;
    private final ListPageCategoryOfBookService listPageCategoryOfBookService;
    private final SaveCategoryOfBook saveCategoryOfBook;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryBook(@PathVariable(value = "id") Long id) {
        deleteCategoryOfBookService.delete(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editCategoryBook(@Valid @PathVariable(value = "id") Long id,@RequestBody CategoryOfBookDTO categoryOfBookDTO) {
        editCategoryOfBookService.editCategoryOfBook(categoryOfBookDTO, id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryOfBookDTO getCategoryBook(@Valid @PathVariable(value = "id") Long id) {
        return getCategoryOfBookService.getCategoryOfBook(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryOfBookDTO> listAllCategoryBook() {
        return listCategoryOfBookService.listAllCategoriesOfBook();
    }
    @GetMapping("/pages")
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryOfBookDTO> listPageCategoryBook(Pageable pageable){
        return listPageCategoryOfBookService.listPageOfCategoryOfBook(pageable);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategoryOfBook(@Valid @RequestBody CategoryOfBookDTO categoryOfBookDTO) {
        saveCategoryOfBook.saveCategoryOfBook(categoryOfBookDTO);
    }


}
