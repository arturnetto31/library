package com.phoebus.library.userlibrary.service;

import com.phoebus.library.userlibrary.UserLibraryDTO;

@FunctionalInterface
public interface GetUserService {
    UserLibraryDTO getUserLibrary(Long id);
}
