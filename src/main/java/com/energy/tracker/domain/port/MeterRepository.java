package com.energy.tracker.domain.port;

import com.energy.tracker.domain.model.Meter;

import java.util.Optional;

public interface MeterRepository {

    Optional<Meter> findById(String id);

    Meter save(Meter meter);
}