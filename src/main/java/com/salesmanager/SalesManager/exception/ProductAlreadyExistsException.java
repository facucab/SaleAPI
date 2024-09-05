package com.salesmanager.SalesManager.exception;

public class ProductAlreadyExistsException extends  RuntimeException{

    public ProductAlreadyExistsException() {
    }
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
