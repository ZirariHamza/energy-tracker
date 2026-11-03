package com.energy.tracker.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BillCalculator {

    public BigDecimal compute(Tariff tariff, long consumptionKwh) {
        BigDecimal kwh = BigDecimal.valueOf(consumptionKwh);

        BigDecimal amount = switch (tariff) {
            case BaseTariff(var pricePerKwh) -> pricePerKwh.multiply(kwh);
        };

        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}