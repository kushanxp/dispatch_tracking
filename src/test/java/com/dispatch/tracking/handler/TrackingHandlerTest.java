package com.dispatch.tracking.handler;

import com.dispatch.tracking.message.DispatchPreparing;
import com.dispatch.tracking.service.TrackingService;
import com.dispatch.tracking.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackingHandlerTest {

    private TrackingService trackingService;

    private TrackingHandler trackingHandler;

    private
    @BeforeEach
    void setUp() {
       trackingService = mock(TrackingService.class);
       trackingHandler = new TrackingHandler(trackingService);
    }

    @Test
    void listener() throws Exception{
        DispatchPreparing dispatchPreparing = TestEventData.buildDispatchPreparing(UUID.randomUUID(), UUID.randomUUID().toString());
        trackingHandler.listener(dispatchPreparing);
        verify(trackingService, times(1)).process(dispatchPreparing);
    }
}