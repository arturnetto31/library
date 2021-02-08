package com.phoebus.library.userlibrary.v1;

import com.phoebus.library.userlibrary.UserLibraryDTO;
import com.phoebus.library.userlibrary.service.DeleteUserService;
import com.phoebus.library.userlibrary.service.EditUserService;
import com.phoebus.library.userlibrary.service.GetUserService;
import com.phoebus.library.userlibrary.service.ListPageUserService;
import com.phoebus.library.userlibrary.service.ListUserService;
import com.phoebus.library.userlibrary.service.SaveUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/v1/user")
public class UserLibraryControllerV1 {
    private final DeleteUserService deleteUserService;
    private final EditUserService editUserService;
    private final GetUserService getUserService;
    private final ListUserService listUserService;
    private final SaveUserService saveUserService;
    private final ListPageUserService listPageUserService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable(value="id") Long id) {
        deleteUserService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody @Valid UserLibraryDTO newUserLibrary) {
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

    @GetMapping(path = {"/page"})
    @ResponseStatus(HttpStatus.OK)
    public Page<UserLibraryDTO> findPage(Pageable pageable) {
        return listPageUserService.listUserOnPage(pageable);
    }

    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editUserLibrary(@Valid @PathVariable(value="id") Long id, @RequestBody UserLibraryDTO userLibraryDTO) {
        editUserService.editUserLibrary(id,userLibraryDTO);
    }

}
