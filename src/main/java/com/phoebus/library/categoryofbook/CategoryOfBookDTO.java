package com.phoebus.library.categoryofbook;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryOfBookDTO {
    private Long id;

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

}
