package com.salesmanager.SalesManager.exception;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException() {
    }

    public OutOfStockException(String message) {
        super(message);
    }


}
