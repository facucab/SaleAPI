package com.salesmanager.SalesManager.exception;

public class SaleNotFoundException extends  RuntimeException{
    public SaleNotFoundException() {
    }

    public SaleNotFoundException(String message) {
        super(message);
    }
}
