package com.phoebus.library.userlibrary.service;

import com.phoebus.library.userlibrary.UserLibrary;
import com.phoebus.library.userlibrary.UserLibraryDTO;
import com.phoebus.library.userlibrary.UserLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListUserServiceImpl implements ListUserService {
    private final UserLibraryRepository repository;

    @Override
    public List<UserLibraryDTO> listUserLibrary() {
        List<UserLibrary> userLibraryList = repository.findAll();
        return UserLibraryDTO.from(userLibraryList);
    }
}
