package com.salesmanager.SalesManager.exception;

public class CategoryNotFoundException extends  RuntimeException{
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException() {
    }
}
