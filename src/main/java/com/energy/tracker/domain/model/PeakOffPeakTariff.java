package com.energy.tracker.domain.model;

import java.math.BigDecimal;

public record PeakOffPeakTariff(BigDecimal peakPrice, BigDecimal offPeakPrice, BigDecimal peakShare)
        implements Tariff {
}