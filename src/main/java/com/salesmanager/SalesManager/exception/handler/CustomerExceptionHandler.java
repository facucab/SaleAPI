package com.salesmanager.SalesManager.exception.handler;

import com.salesmanager.SalesManager.exception.CustomerNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public Map<String,String> CustomerNotFoundHandler(CustomerNotFoundException e )
    {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getLocalizedMessage());
        return response;
    }
}
