package com.example.price_monitor.exception;

public class ItemAlreadyExistsException extends RuntimeException {

    public ItemAlreadyExistsException(String url) {
        super("Item with url already exists: " + url);
    }
}