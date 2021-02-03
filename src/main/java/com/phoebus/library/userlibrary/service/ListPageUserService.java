package com.phoebus.library.userlibrary.service;


import com.phoebus.library.userlibrary.UserLibraryDTO;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface ListPageUserService {
    Page<UserLibraryDTO> listUserOnPage(Integer page, Integer size);
}
