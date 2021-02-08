package com.phoebus.library.userlibrary.builders;

import com.phoebus.library.userlibrary.UserLibraryDTO;

public class UserLibraryBuilderDTO {

    public static UserLibraryDTO.Builder createUserLibraryDTO() {
        return UserLibraryDTO.builder()
                .id(1L)
                .name("Test")
                .age(22)
                .phone("0000-0000")
                .email("teste@teste.com")
                .gender("M");
    }
}
