package com.energy.tracker.domain.model;

import org.junit.jupiter.api.Test;

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
}