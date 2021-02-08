package com.phoebus.library.userlibrary.service;

import com.phoebus.library.exceptions.UserNotFoundException;
import com.phoebus.library.userlibrary.UserLibraryDTO;
import com.phoebus.library.userlibrary.UserLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetUserServiceImpl implements GetUserService {
    private final UserLibraryRepository repository;

    @Override
    public UserLibraryDTO getUserLibrary(Long id) {
        return UserLibraryDTO.from(repository.findById(id).orElseThrow(UserNotFoundException::new));
    }
}
