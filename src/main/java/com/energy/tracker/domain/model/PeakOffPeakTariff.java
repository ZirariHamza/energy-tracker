package com.energy.tracker.domain.model;

import java.math.BigDecimal;

public record PeakOffPeakTariff(BigDecimal peakPrice, BigDecimal offPeakPrice, BigDecimal peakShare)
        implements Tariff {

    public PeakOffPeakTariff {
        if (peakShare == null
                || peakShare.compareTo(BigDecimal.ZERO) < 0
                || peakShare.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Peak share must be between 0 and 1");
        }
    }
}