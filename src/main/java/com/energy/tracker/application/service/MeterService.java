package com.energy.tracker.application.service;

import com.energy.tracker.application.exception.MeterAlreadyExistsException;
import com.energy.tracker.application.exception.MeterNotFoundException;
import com.energy.tracker.domain.model.BillCalculator;
import com.energy.tracker.domain.model.Meter;
import com.energy.tracker.domain.model.Reading;
import com.energy.tracker.domain.port.MeterRepository;
import com.energy.tracker.domain.model.Tariff;


import java.math.BigDecimal;
import java.time.LocalDate;

public class MeterService {

    private final MeterRepository repository;
    private final BillCalculator billCalculator = new BillCalculator();

    public MeterService(MeterRepository repository) {
        this.repository = repository;
    }

    public Meter createMeter(String id) {
        if (repository.findById(id).isPresent()) {
            throw new MeterAlreadyExistsException(id);
        }
        return repository.save(new Meter(id));
    }

    public Meter get(String id) {
        return repository.findById(id).orElseThrow(() -> new MeterNotFoundException(id));
    }

    public Meter addReading(String meterId, LocalDate date, long index) {
        Meter meter = repository.findById(meterId)
                .orElseThrow(() -> new MeterNotFoundException(meterId));
        meter.addReading(new Reading(date, index));
        return repository.save(meter);
    }

    public BigDecimal bill(String meterId, LocalDate from, LocalDate to, Tariff tariff) {
        Meter meter = repository.findById(meterId)
                .orElseThrow(() -> new MeterNotFoundException(meterId));
        long consumption = meter.consumption(from, to);
        return billCalculator.compute(tariff, consumption);
    }

    public long consumption(String meterId, LocalDate from, LocalDate to) {
        return get(meterId).consumption(from, to);
    }

    public boolean hasAnomaly(String meterId) {
        return get(meterId).hasAnomaly();
    }
}