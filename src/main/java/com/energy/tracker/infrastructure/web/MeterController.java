package com.energy.tracker.infrastructure.web;

import com.energy.tracker.application.service.MeterService;
import com.energy.tracker.infrastructure.web.ApiDtos.CreateMeterRequest;
import com.energy.tracker.infrastructure.web.ApiDtos.MeterResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meters")
public class MeterController {

    private final MeterService meterService;

    public MeterController(MeterService meterService) {
        this.meterService = meterService;
    }

    @PostMapping
    public ResponseEntity<MeterResponse> create(@Valid @RequestBody CreateMeterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MeterResponse.from(meterService.createMeter(request.id())));
    }
}