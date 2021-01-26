package com.phoebus.library.userlibrary.service;


import com.phoebus.library.userlibrary.UserLibrary;

@FunctionalInterface
public interface SaveUserService {
    public void save(UserLibrary userLibrary);
}
