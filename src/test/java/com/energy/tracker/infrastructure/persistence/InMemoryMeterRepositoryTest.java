package com.energy.tracker.infrastructure.persistence;

import com.energy.tracker.domain.model.Meter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryMeterRepositoryTest {

    private final InMemoryMeterRepository repository = new InMemoryMeterRepository();

    @Test
    void shouldSaveAndFindMeter() {
        repository.save(new Meter("M1"));

        assertEquals("M1", repository.findById("M1").orElseThrow().id());
    }

    @Test
    void shouldReturnEmptyForUnknownMeter() {
        assertTrue(repository.findById("UNKNOWN").isEmpty());
    }
}