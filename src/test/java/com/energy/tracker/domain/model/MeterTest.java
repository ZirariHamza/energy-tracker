package com.energy.tracker.domain.model;

import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MeterTest {

    @Test
    void shouldRejectReadingWhenIndexIsLowerThanLastReading(){
        Meter meter = new Meter("METER-1");
        meter.addReading(new Reading(LocalDate.of(2026, 1, 1), 1000));

        assertThrows(IllegalArgumentException.class,
                () -> meter.addReading(new Reading(LocalDate.of(2026, 1, 1), 900)));
    }

    @Test
    void shouldRejectReadingWithFutureDate() {
        Meter meter = new Meter("METER-1");
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class,
                () -> meter.addReading(new Reading(tomorrow, 1000)));
    }

    @Test
    void shouldRejectReadingDatedBeforePreviousReading() {
        Meter meter = new Meter("METER-1");
        meter.addReading(new Reading(LocalDate.of(2026, 2, 1), 1000));

        assertThrows(IllegalArgumentException.class,
                () -> meter.addReading(new Reading(LocalDate.of(2026, 1, 1), 1100)));
    }

    @Test
    void shouldRejectNegativeIndex() {
        assertThrows(IllegalArgumentException.class,
                () -> new Reading(LocalDate.of(2026, 1, 1), -1));
    }

    @Test
    void shouldAcceptFirstReading() {
        Meter meter = new Meter("METER-1");

        assertDoesNotThrow(() -> meter.addReading(new Reading(LocalDate.of(2026, 1, 1), 1000)));
    }

    @Test
    void shouldComputeConsumptionBetweenTwoDates() {
        Meter meter = new Meter("METER-1");
        meter.addReading(new Reading(LocalDate.of(2026, 1, 1), 1000));
        meter.addReading(new Reading(LocalDate.of(2026, 2, 1), 1250));

        long consumption = meter.consumption(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 2, 1));

        assertEquals(250, consumption);
    }

    @Test
    void shouldRejectConsumptionWhenPeriodHasNoReading() {
        Meter meter = new Meter("METER-1");

        assertThrows(IllegalArgumentException.class,
                () -> meter.consumption(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 2, 1)));
    }

    @Test
    void shouldRejectConsumptionWhenPeriodHasOnlyOneReading() {
        Meter meter = new Meter("METER-1");
        meter.addReading(new Reading(LocalDate.of(2026, 1, 1), 1000));
        meter.addReading(new Reading(LocalDate.of(2026, 3, 1), 1500));

        assertThrows(IllegalArgumentException.class,
                () -> meter.consumption(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 2, 1)));
    }
}
