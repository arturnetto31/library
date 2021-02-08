package com.phoebus.library.userlibrary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class UserLibraryDTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Name may not be empty")
    @Size(min = 2)
    private String name;
    @NotNull
    @Min(2)
    private int age;
    @NotEmpty(message = "Phone may not be empty")
    @Min(8)
    private String phone;
    @NotEmpty(message = "Email may not be empty")
    @Min(8)
    private String email;
    @NotEmpty(message = "Gender may not be empty")
    @Max(1)
    private String gender;

    public static UserLibraryDTO from(UserLibrary userLibrary) {
        return UserLibraryDTO.builder()
                .id(userLibrary.getId())
                .age(userLibrary.getAge())
                .name(userLibrary.getName())
                .phone(userLibrary.getPhone())
                .email(userLibrary.getEmail())
                .gender(userLibrary.getGender())
                .build();
    }
    public static List<UserLibraryDTO> from(List<UserLibrary> users) {
        List<UserLibraryDTO> userLibraryDTOS = new ArrayList<>();
        for (UserLibrary userLibrary : users) {
            userLibraryDTOS.add(UserLibraryDTO.from(userLibrary));
        }
        return userLibraryDTOS;
    }

    public static Page<UserLibraryDTO> from(Page<UserLibrary> pages) {
        return pages.map(UserLibraryDTO::from);
    }
}
