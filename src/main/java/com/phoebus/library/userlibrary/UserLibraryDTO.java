package com.phoebus.library.userlibrary;


import lombok.*;

import javax.validation.constraints.NotEmpty;
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
    private String name;
    @NotEmpty(message = "Age may not be empty")
    private int age;
    @NotEmpty(message = "Phone may not be empty")
    private String phone;
    @NotEmpty(message = "Email may not be empty")
    private String email;
    @NotEmpty(message = "Gender may not be empty")
    private String gender;

    public static UserLibraryDTO from(UserLibrary userLibrary) {
        return UserLibraryDTO.builder()
                .id(userLibrary.getId())
                .age(userLibrary.getAge())
                .name(userLibrary.getName())
                .phone(userLibrary.getPhone())
                .build();
    }
    public static List<UserLibraryDTO> from(List<UserLibrary> users) {
        List<UserLibraryDTO> userLibraryDTOS = new ArrayList<>();
        for (UserLibrary userLibrary : users) {
            userLibraryDTOS.add(UserLibraryDTO.from(userLibrary));
        }
        return userLibraryDTOS;
    }
}
