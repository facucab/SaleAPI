package com.salesmanager.SalesManager.exception.handler;

import com.salesmanager.SalesManager.exception.ProductAlreadyExistsException;
import com.salesmanager.SalesManager.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> ProductAlreadyExistsHandler(ProductAlreadyExistsException e)
    {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getLocalizedMessage());
        return response;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> ProductNotFoundException(ProductNotFoundException e)
    {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getLocalizedMessage());
        return response;
    }
}
