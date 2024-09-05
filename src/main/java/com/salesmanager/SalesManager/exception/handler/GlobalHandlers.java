package com.salesmanager.SalesManager.exception.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlers {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> MethodNotValidHandler(MethodArgumentNotValidException e)
    {
        Map<String, String> response = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(
                (error)-> {response.put("menssage", error.getDefaultMessage());}
        );

        return response;
    }

}
