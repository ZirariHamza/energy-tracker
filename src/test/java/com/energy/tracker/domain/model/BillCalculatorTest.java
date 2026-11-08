package com.energy.tracker.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BillCalculatorTest {

    private final BillCalculator calculator = new BillCalculator();

    @Test
    void shouldComputeBillForBaseTariff() {
        Tariff tariff = new BaseTariff(new BigDecimal("0.20"));

        BigDecimal bill = calculator.compute(tariff, 250);

        assertEquals(new BigDecimal("50.00"), bill);
    }

    @Test
    void shouldComputeBillForPeakOffPeakTariff() {
        // unit price = 0.25 * 0.40 + 0.15 * 0.60 = 0.19
        Tariff tariff = new PeakOffPeakTariff(
                new BigDecimal("0.25"), new BigDecimal("0.15"), new BigDecimal("0.40"));

        BigDecimal bill = calculator.compute(tariff, 100);

        assertEquals(new BigDecimal("19.00"), bill);
    }

    @Test
    void shouldRejectNegativeConsumption() {
        Tariff tariff = new BaseTariff(new BigDecimal("0.20"));

        assertThrows(IllegalArgumentException.class, () -> calculator.compute(tariff, -1));
    }
}