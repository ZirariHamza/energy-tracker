package com.energy.tracker.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnomalyDetectorTest {

    @Test
    void shouldFlagJustAboveTwiceTheAverage() {
        assertTrue(AnomalyDetector.isAnomaly(List.of(100L, 100L, 201L)));
    }

    @Test
    void shouldNotFlagExactlyTwiceTheAverage() {
        assertFalse(AnomalyDetector.isAnomaly(List.of(100L, 100L, 200L)));
    }

    @Test
    void shouldNotFlagWithoutHistory() {
        assertFalse(AnomalyDetector.isAnomaly(List.of()));
        assertFalse(AnomalyDetector.isAnomaly(List.of(500L)));
    }

    @Test
    void shouldFlagAnomalyWhenLatestConsumptionIsMoreThanTwiceTheAverage() {
        Meter meter = new Meter("METER-1");
        meter.addReading(new Reading(LocalDate.of(2026, 1, 1), 1000));
        meter.addReading(new Reading(LocalDate.of(2026, 2, 1), 1100));
        meter.addReading(new Reading(LocalDate.of(2026, 3, 1), 1200));
        meter.addReading(new Reading(LocalDate.of(2026, 4, 1), 1500));

        assertTrue(meter.hasAnomaly());
    }

    @Test
    void shouldNotFlagAnomalyForNormalConsumption() {
        Meter meter = new Meter("METER-1");
        meter.addReading(new Reading(LocalDate.of(2026, 1, 1), 1000));
        meter.addReading(new Reading(LocalDate.of(2026, 2, 1), 1100));
        meter.addReading(new Reading(LocalDate.of(2026, 3, 1), 1200));
        meter.addReading(new Reading(LocalDate.of(2026, 4, 1), 1320));

        assertFalse(meter.hasAnomaly());
    }
}