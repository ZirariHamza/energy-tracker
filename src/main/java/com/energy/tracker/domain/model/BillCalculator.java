package com.energy.tracker.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BillCalculator {

    public BigDecimal compute(Tariff tariff, long consumptionKwh) {
        if (consumptionKwh < 0) {
            throw new IllegalArgumentException("Consumption cannot be negative");
        }

        BigDecimal kwh = BigDecimal.valueOf(consumptionKwh);

        BigDecimal amount = switch (tariff) {
            case BaseTariff(var pricePerKwh) -> pricePerKwh.multiply(kwh);
            case PeakOffPeakTariff t -> BigDecimal.ZERO;
        };

        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}