package com.phoebus.library.userlibrary.service;


import com.phoebus.library.userlibrary.UserLibrary;
import com.phoebus.library.userlibrary.UserLibraryDTO;

@FunctionalInterface
public interface GetUserService {
    public UserLibraryDTO getUserLibrary(Long id);
}
