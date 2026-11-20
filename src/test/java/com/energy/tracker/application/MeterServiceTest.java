package com.energy.tracker.application;

import com.energy.tracker.application.exception.MeterAlreadyExistsException;
import com.energy.tracker.application.exception.MeterNotFoundException;
import com.energy.tracker.application.service.MeterService;
import com.energy.tracker.domain.model.Meter;
import com.energy.tracker.domain.model.Reading;
import com.energy.tracker.domain.port.MeterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeterServiceTest {

    @Mock
    MeterRepository repository;

    @InjectMocks
    MeterService service;

    @Test
    void shouldCreateMeter() {
        when(repository.findById("M1")).thenReturn(Optional.empty());
        when(repository.save(any(Meter.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Meter created = service.createMeter("M1");

        assertEquals("M1", created.id());
        verify(repository).save(any(Meter.class));
    }

    @Test
    void shouldRejectDuplicateMeter() {
        when(repository.findById("M1")).thenReturn(Optional.of(new Meter("M1")));

        assertThrows(MeterAlreadyExistsException.class, () -> service.createMeter("M1"));
        verify(repository, never()).save(any(Meter.class));
    }

    @Test
    void shouldAddReadingAndSaveMeter() {
        Meter meter = new Meter("M1");
        when(repository.findById("M1")).thenReturn(Optional.of(meter));
        when(repository.save(meter)).thenReturn(meter);

        service.addReading("M1", LocalDate.of(2026, 1, 1), 1000);

        assertEquals(1, meter.readings().size());
        verify(repository).save(meter);
    }

    @Test
    void shouldFailWhenMeterDoesNotExist() {
        when(repository.findById("UNKNOWN")).thenReturn(Optional.empty());

        assertThrows(MeterNotFoundException.class,
                () -> service.addReading("UNKNOWN", LocalDate.of(2026, 1, 1), 10));
    }

    @Test
    void shouldNotSaveWhenDomainRuleIsViolated() {
        Meter meter = new Meter("M1");
        meter.addReading(new Reading(LocalDate.of(2026, 1, 1), 1000));
        when(repository.findById("M1")).thenReturn(Optional.of(meter));

        assertThrows(IllegalArgumentException.class,
                () -> service.addReading("M1", LocalDate.of(2026, 2, 1), 900));
        verify(repository, never()).save(any(Meter.class));
    }
}