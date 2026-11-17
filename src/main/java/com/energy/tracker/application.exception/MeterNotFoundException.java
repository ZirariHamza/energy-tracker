package com.energy.tracker.application.exception;

public class MeterNotFoundException extends RuntimeException {

    public MeterNotFoundException(String meterId) {
        super("Meter not found: " + meterId);
    }
}