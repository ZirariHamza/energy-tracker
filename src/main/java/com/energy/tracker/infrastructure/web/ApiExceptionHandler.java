package com.energy.tracker.infrastructure.web;

import com.energy.tracker.application.exception.MeterAlreadyExistsException;
import com.energy.tracker.infrastructure.web.ApiDtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MeterAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> conflict(MeterAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
    }
}