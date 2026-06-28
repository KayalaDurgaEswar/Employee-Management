package com.practice.employee_management.exception;

public class DuplicateResourceException extends RuntimeException{

    public DuplicateResourceException(String msg){
        super(msg);
    }
}
