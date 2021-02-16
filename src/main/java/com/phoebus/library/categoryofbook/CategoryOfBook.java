package com.phoebus.library.categoryofbook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TB_CATEGORY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class CategoryOfBook implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    public static CategoryOfBook to(CategoryOfBookDTO categoryOfBookDTO) {
        return CategoryOfBook.builder()
                .id(categoryOfBookDTO.getId())
                .name(categoryOfBookDTO.getName())
                .build();
    }

    public static List<CategoryOfBook> to(List<CategoryOfBookDTO> categoryOfBookDTOS) {
        List<CategoryOfBook> categoryOfBookList = new ArrayList<>();
        for(CategoryOfBookDTO categoryOfBookDTO : categoryOfBookDTOS) {
            categoryOfBookList.add(CategoryOfBook.to(categoryOfBookDTO));
        }
        return categoryOfBookList;
    }

    public static Page<CategoryOfBook> to(Page<CategoryOfBookDTO> pages) {
        return pages.map(CategoryOfBook::to);
    }

}
