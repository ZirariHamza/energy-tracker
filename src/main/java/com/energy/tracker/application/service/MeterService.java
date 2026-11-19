package com.energy.tracker.application.service;

import com.energy.tracker.application.exception.MeterAlreadyExistsException;
import com.energy.tracker.domain.model.Meter;
import com.energy.tracker.domain.port.MeterRepository;

public class MeterService {

    private final MeterRepository repository;

    public MeterService(MeterRepository repository) {
        this.repository = repository;
    }

    public Meter createMeter(String id) {
        if (repository.findById(id).isPresent()) {
            throw new MeterAlreadyExistsException(id);
        }
        return repository.save(new Meter(id));
    }
}