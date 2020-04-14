package com.example.demo.errors.exception;

public class ListIsEmptyException extends RuntimeException{ 
    public ListIsEmptyException(String message){
        super(message);
    }
}