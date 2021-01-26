package com.phoebus.library.userlibrary.v1;


import com.phoebus.library.userlibrary.UserLibrary;
import com.phoebus.library.userlibrary.UserLibraryDTO;
import com.phoebus.library.userlibrary.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/v1/users")
public class UserLibraryControllerV1 {
    private final DeleteUserService deleteUserService;
    private final EditUserService editUserService;
    private final GetUserService getUserService;
    private final ListUserService listUserService;
    private final SaveUserService saveUserService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable(value="id") Long id) {
        deleteUserService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody @Valid UserLibrary newUserLibrary) {
        saveUserService.save(newUserLibrary);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserLibraryDTO> listUsersLibrary() {
        return listUserService.listUserLibrary();
    }

    @GetMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserLibraryDTO userLibraryDTObyID(@PathVariable(value="id") Long id) {
        return getUserService.getUserLibrary(id);
    }

    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editUserLibrary(@Valid @PathVariable(value="id") Long id, @RequestBody UserLibraryDTO userLibraryDTO) {
        editUserService.editUserLibrary(id,userLibraryDTO);
    }

}
