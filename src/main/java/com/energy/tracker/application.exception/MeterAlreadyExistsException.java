package com.energy.tracker.application.exception;

public class MeterAlreadyExistsException extends RuntimeException {

    public MeterAlreadyExistsException(String meterId) {
        super("Meter already exists: " + meterId);
    }
}