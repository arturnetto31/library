package com.phoebus.library.userlibrary.service;

import com.phoebus.library.exceptions.UserInconsistencyInDataException;
import com.phoebus.library.userlibrary.UserLibrary;
import com.phoebus.library.userlibrary.UserLibraryDTO;
import com.phoebus.library.userlibrary.UserLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditUserServiceImpl implements EditUserService {
    private final UserLibraryRepository repository;

    @Override
    public void editUserLibrary(Long id, UserLibraryDTO userLibraryDTO) {
       UserLibrary userLibrary = repository.findById(id).orElseThrow(UserInconsistencyInDataException::new);

       userLibrary.setAge(userLibraryDTO.getAge());
       userLibrary.setEmail(userLibraryDTO.getEmail());
       userLibrary.setName(userLibraryDTO.getName());
       userLibrary.setGender(userLibraryDTO.getGender());
       userLibrary.setPhone(userLibraryDTO.getPhone());

        repository.save(userLibrary);
    }
}
