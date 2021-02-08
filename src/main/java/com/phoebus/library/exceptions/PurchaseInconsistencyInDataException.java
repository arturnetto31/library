package com.phoebus.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PurchaseInconsistencyInDataException extends RuntimeException{
    public PurchaseInconsistencyInDataException(){
        super("Incosistency in Purchase Data");
    }
}
