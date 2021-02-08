package com.phoebus.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookInconsistencyInDataException extends RuntimeException{
    public BookInconsistencyInDataException(){
        super("Inconsistency in book data");
    }
}
