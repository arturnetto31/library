package com.phoebus.library.userlibrary.service;

import com.phoebus.library.userlibrary.UserLibraryDTO;

@FunctionalInterface
public interface SaveUserService {
    void save(UserLibraryDTO userLibraryDTO);
}
