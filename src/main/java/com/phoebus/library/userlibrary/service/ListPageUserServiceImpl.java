package com.phoebus.library.userlibrary.service;


import com.phoebus.library.userlibrary.UserLibraryDTO;
import com.phoebus.library.userlibrary.UserLibraryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListPageUserServiceImpl implements ListPageUserService{

    private UserLibraryRepository repository;

    @Override
    public Page<UserLibraryDTO> listUserOnPage(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page,size, Sort.Direction.ASC, "id");

        return UserLibraryDTO.from(repository.findAll(pageRequest));
    }
}
