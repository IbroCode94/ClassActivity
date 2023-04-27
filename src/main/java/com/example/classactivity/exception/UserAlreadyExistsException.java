package com.example.classactivity.exception;

public class UserAlreadyExistsException extends  RuntimeException{
    public UserAlreadyExistsException (String message) {super (message);}
}
