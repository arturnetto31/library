package com.phoebus.library.userlibrary.service;

import com.phoebus.library.userlibrary.UserLibraryDTO;

@FunctionalInterface
public interface EditUserService {
    void editUserLibrary(Long id, UserLibraryDTO userLibraryDTO);
}
