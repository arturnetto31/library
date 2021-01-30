package com.phoebus.library.userlibrary;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="TB_USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class UserLibrary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID")
    private Long id;

    private String name;
    private int age;
    private String phone;

    @Column(unique = true)
    private String email;

    private String gender;

    public static UserLibrary to(UserLibraryDTO userLibraryDTO) {
        return UserLibrary.builder()
                .id(userLibraryDTO.getId())
                .name(userLibraryDTO.getName())
                .age(userLibraryDTO.getAge())
                .phone(userLibraryDTO.getPhone())
                .email(userLibraryDTO.getEmail())
                .gender(userLibraryDTO.getGender())
                .build();
    }

    public static List<UserLibrary> to(List<UserLibraryDTO> userLibraryDTOList) {
        List<UserLibrary> userLibraryList = new ArrayList<>();
        for(UserLibraryDTO userLibraryDTO : userLibraryDTOList) {
            userLibraryList.add(UserLibrary.to(userLibraryDTO));
        }
        return userLibraryList;
    }
}
