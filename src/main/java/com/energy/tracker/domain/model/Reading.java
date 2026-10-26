package com.energy.tracker.domain.model;

import java.time.LocalDate;

public record Reading(LocalDate date, long index) {

    public Reading {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        }
    }
}
