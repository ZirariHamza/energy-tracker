package com.energy.tracker.domain.model;

import java.math.BigDecimal;

public record BaseTariff(BigDecimal pricePerKwh) implements Tariff {
}