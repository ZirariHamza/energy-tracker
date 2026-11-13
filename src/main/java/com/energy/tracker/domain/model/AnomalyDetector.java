package com.energy.tracker.domain.model;

import java.util.List;

public final class AnomalyDetector {

    private AnomalyDetector() {
    }

    public static boolean isAnomaly(List<Long> consumptions) {
        if (consumptions.size() < 2) {
            return false;
        }
        long latest = consumptions.getLast();
        List<Long> previous = consumptions.subList(0, consumptions.size() - 1);
        long sum = previous.stream().mapToLong(Long::longValue).sum();
        return latest * previous.size() > 2 * sum;
    }
}