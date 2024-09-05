package com.salesmanager.SalesManager.exception;

public class CategoryAlreadyExistException extends  RuntimeException{
    public CategoryAlreadyExistException() {
    }

    public CategoryAlreadyExistException(String message) {
        super(message);
    }
}
