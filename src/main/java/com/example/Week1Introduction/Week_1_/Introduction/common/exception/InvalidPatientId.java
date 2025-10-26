package com.example.Week1Introduction.Week_1_.Introduction.common.exception;

public class InvalidPatientId  extends RuntimeException{
    public InvalidPatientId(String message)
    {
        super(message);
    }
}
