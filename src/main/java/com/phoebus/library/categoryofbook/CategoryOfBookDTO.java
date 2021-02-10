package com.phoebus.library.categoryofbook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryOfBookDTO {
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotEmpty
    @Size(min = 2)
    private String name;

    public static CategoryOfBookDTO from(CategoryOfBook categoryOfBook) {
        return CategoryOfBookDTO.builder()
                .id(categoryOfBook.getId())
                .name(categoryOfBook.getName())
                .build();
    }

    public static List<CategoryOfBookDTO> from(List<CategoryOfBook> listCategoryOfBook) {
        List<CategoryOfBookDTO> listCategoryBookDTO = new ArrayList<>();

        for (CategoryOfBook categoryOfBook: listCategoryOfBook) {
            listCategoryBookDTO.add(CategoryOfBookDTO.from(categoryOfBook));
        }

        return listCategoryBookDTO;

    }

    public static Page<CategoryOfBookDTO> from(Page<CategoryOfBook> pages) {
        return pages.map(CategoryOfBookDTO::from);
    }

}
