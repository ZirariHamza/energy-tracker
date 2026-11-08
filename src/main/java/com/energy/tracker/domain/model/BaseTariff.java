package com.energy.tracker.domain.model;

import java.math.BigDecimal;

public record BaseTariff(BigDecimal pricePerKwh) implements Tariff {

    public BaseTariff {
        if (pricePerKwh == null || pricePerKwh.signum() < 0) {
            throw new IllegalArgumentException("Price per kWh must be zero or positive");
        }
    }
}