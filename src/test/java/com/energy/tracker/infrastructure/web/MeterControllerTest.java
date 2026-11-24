package com.energy.tracker.infrastructure.web;

import com.energy.tracker.application.service.MeterService;
import com.energy.tracker.domain.model.Meter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MeterControllerTest {

    @Mock
    MeterService meterService;

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new MeterController(meterService)).build();
    }

    @Test
    void shouldCreateMeter() throws Exception {
        when(meterService.createMeter("M1")).thenReturn(new Meter("M1"));

        mvc.perform(post("/api/meters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"M1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("M1"));
    }
}