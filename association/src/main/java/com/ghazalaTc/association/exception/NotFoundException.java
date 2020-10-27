package com.ghazalaTc.association.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "NotFoundException not Found")
public class NotFoundException extends  RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(String message,Throwable cause)

    {
        super(message,cause);
    }
}
