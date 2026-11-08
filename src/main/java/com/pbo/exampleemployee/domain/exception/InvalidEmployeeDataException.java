package com.pbo.exampleemployee.domain.exception;

public class InvalidEmployeeDataException extends RuntimeException {
    public InvalidEmployeeDataException(String message) {
        super(message);
    }
}
