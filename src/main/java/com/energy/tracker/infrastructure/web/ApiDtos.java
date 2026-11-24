package com.energy.tracker.infrastructure.web;

import com.energy.tracker.domain.model.Meter;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public final class ApiDtos {

    private ApiDtos() {
    }

    public record CreateMeterRequest(@NotBlank String id) {
    }

    public record ReadingDto(LocalDate date, long index) {
    }

    public record MeterResponse(String id, List<ReadingDto> readings) {

        public static MeterResponse from(Meter meter) {
            return new MeterResponse(meter.id(), meter.readings().stream()
                    .map(r -> new ReadingDto(r.date(), r.index()))
                    .toList());
        }
    }

    public record ErrorResponse(String message) {
    }
}