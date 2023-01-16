package com.demoproject.demo.advice.exception;

/**
 * 1. custom exception을 만들어줌
 */
public class UserNotFoundCException extends RuntimeException{
    public UserNotFoundCException() {
        super();
    }
}
