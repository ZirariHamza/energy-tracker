package com.energy.tracker.domain.model;

import java.time.LocalDate;

public record Reading(LocalDate date, long index) {
}
