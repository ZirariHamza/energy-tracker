package com.energy.tracker.infrastructure.persistence;

import com.energy.tracker.domain.model.Meter;
import com.energy.tracker.domain.port.MeterRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryMeterRepository implements MeterRepository {

    private final Map<String, Meter> meters = new ConcurrentHashMap<>();

    @Override
    public Optional<Meter> findById(String id) {
        return Optional.ofNullable(meters.get(id));
    }

    @Override
    public Meter save(Meter meter) {
        meters.put(meter.id(), meter);
        return meter;
    }
}