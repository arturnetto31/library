package com.phoebus.library.userlibrary.service;

import com.phoebus.library.exceptions.UserNotFoundException;
import com.phoebus.library.userlibrary.UserLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteUserServiceImpl implements DeleteUserService{

    private final UserLibraryRepository repository;

    @Override
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new UserNotFoundException();
        }
            repository.deleteById(id);
    }
}
