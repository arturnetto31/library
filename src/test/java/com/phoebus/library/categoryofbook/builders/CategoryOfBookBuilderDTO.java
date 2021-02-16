package com.phoebus.library.categoryofbook.builders;

import com.phoebus.library.categoryofbook.CategoryOfBookDTO;

public class CategoryOfBookBuilderDTO {
    public static CategoryOfBookDTO.Builder createCategoryOfBookDTO() {
        return CategoryOfBookDTO.builder()
                .id(1L)
                .name("action");

    }

}
