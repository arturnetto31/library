package com.phoebus.library.userlibrary.service;

import com.phoebus.library.userlibrary.UserLibraryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPageUserService {
    Page<UserLibraryDTO> listUserOnPage(Pageable pageable);
}
