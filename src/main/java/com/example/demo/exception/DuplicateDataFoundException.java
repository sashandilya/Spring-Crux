package com.example.demo.exception;

public class DuplicateDataFoundException extends Exception{
    public DuplicateDataFoundException(String message) {
        super(message);
    }
}
