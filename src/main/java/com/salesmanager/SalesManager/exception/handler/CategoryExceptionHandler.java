package com.salesmanager.SalesManager.exception.handler;

import com.salesmanager.SalesManager.exception.CategoryAlreadyExistException;
import com.salesmanager.SalesManager.exception.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CategoryExceptionHandler {

    @ExceptionHandler(CategoryAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> CategoryAlreadyExistHandler(CategoryAlreadyExistException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getLocalizedMessage());
        return response;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> CategoryNotFoundHandler(CategoryNotFoundException e)
    {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getLocalizedMessage());
        return response;
    }
}
