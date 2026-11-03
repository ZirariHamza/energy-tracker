package com.energy.tracker.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BillCalculatorTest {

    private final BillCalculator calculator = new BillCalculator();

    @Test
    void shouldComputeBillForBaseTariff() {
        Tariff tariff = new BaseTariff(new BigDecimal("0.20"));

        BigDecimal bill = calculator.compute(tariff, 250);

        assertEquals(new BigDecimal("50.00"), bill);
    }
}