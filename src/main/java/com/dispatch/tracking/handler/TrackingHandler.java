package com.dispatch.tracking.handler;

import com.dispatch.tracking.message.DispatchPreparing;
import com.dispatch.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrackingHandler {

    private final TrackingService trackingService;

    @KafkaListener(
            id = "dispatchTracking",
            topics = "dispatch.tracking",
            groupId = "dispatch.order.crated.consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listener(DispatchPreparing dispatchPreparing) {
        log.info(dispatchPreparing.toString());
        try{
            trackingService.process(dispatchPreparing);
        } catch (Exception e) {
            log.error("Tracking Process Failed! " +e);
        }

    }
}
