package com.salesmanager.SalesManager.exception.handler;

import com.salesmanager.SalesManager.exception.OutOfStockException;
import com.salesmanager.SalesManager.exception.SaleNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class SaleExceptionHandler {

    @ExceptionHandler(OutOfStockException.class)
    public Map<String, String> OutOfStockExceptionHanlder(OutOfStockException e)
    {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getLocalizedMessage());
        return response;
    }

    @ExceptionHandler(SaleNotFoundException.class)
    public Map<String, String> SaleNotFoundExceptionHandler(SaleNotFoundException e)
    {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getLocalizedMessage());
        return response;
    }
}
