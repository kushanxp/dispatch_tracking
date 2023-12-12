package com.dispatch.tracking.service;

import com.dispatch.tracking.message.DispatchPreparing;
import com.dispatch.tracking.message.TrackingStatusUpdated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.configuration.IMockitoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TrackingServiceTest {

    private KafkaTemplate<String, Object> kafkaTemplate;
    private TrackingService trackingService;

    @BeforeEach
    void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        trackingService = new TrackingService(kafkaTemplate);
    }

    @Test
    void process() throws Exception {
        when(kafkaTemplate.send(anyString(), any(TrackingStatusUpdated.class))).thenReturn(mock(CompletableFuture.class));
        DispatchPreparing dispatchPreparing = DispatchPreparing.builder().orderId(UUID.randomUUID()).item(UUID.randomUUID().toString()).build();
        trackingService.process(dispatchPreparing);

        verify(kafkaTemplate, times(1)).send(eq("tracking.status"), any(TrackingStatusUpdated.class));
    }
}