package com.phoebus.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryOfBookInconsistencyInDataException extends RuntimeException{
    public CategoryOfBookInconsistencyInDataException() {
        super("Inconsistency in Category of Book data");
    }
}
