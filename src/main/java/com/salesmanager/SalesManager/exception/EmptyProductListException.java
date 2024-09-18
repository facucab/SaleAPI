package com.salesmanager.SalesManager.exception;

public class EmptyProductListException extends  RuntimeException{
    public EmptyProductListException() {
    }

    public EmptyProductListException(String message) {
        super(message);
    }
}
