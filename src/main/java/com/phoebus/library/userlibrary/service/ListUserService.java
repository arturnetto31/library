package com.phoebus.library.userlibrary.service;


import com.phoebus.library.userlibrary.UserLibrary;
import com.phoebus.library.userlibrary.UserLibraryDTO;

import java.util.List;

@FunctionalInterface
public interface ListUserService {
    public List<UserLibraryDTO> listUserLibrary();
}
