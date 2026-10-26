package com.energy.tracker.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Meter {

    private final String id;
    private final List<Reading> readings = new ArrayList<>();

    public Meter(String id) {
        this.id = id;
    }

    public void addReading(Reading reading) {
        if (reading.date().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Reading date cannot be in the future");
        }
        if (!readings.isEmpty() && reading.date().isBefore(readings.getLast().date())) {
            throw new IllegalArgumentException("Reading date cannot be before the previous reading");
        }
        if (!readings.isEmpty() && reading.index() < readings.getLast().index()) {
            throw new IllegalArgumentException("Index cannot decrease");
        }
        readings.add(reading);
    }

    public long consumption(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("Start date must not be after end date");
        }
        List<Reading> inPeriod = readingsBetween(from, to);
        if (inPeriod.size() < 2) {
            throw new IllegalArgumentException("At least two readings are required in the period");
        }
        return inPeriod.getLast().index() - inPeriod.getFirst().index();
    }

    private List<Reading> readingsBetween(LocalDate from, LocalDate to) {
        return readings.stream()
                .filter(r -> !r.date().isBefore(from) && !r.date().isAfter(to))
                .toList();
    }
}
