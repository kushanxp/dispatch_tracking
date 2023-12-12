package com.dispatch.tracking.service;

import com.dispatch.tracking.message.DispatchPreparing;
import com.dispatch.tracking.message.TrackingStatusUpdated;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TrackingService {

    private final static String DISPATCH_TRACKING_STATUS = "tracking.status";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    public void process(DispatchPreparing dispatchPreparing) throws Exception{
        TrackingStatusUpdated statusUpdated = TrackingStatusUpdated.builder().orderId(dispatchPreparing.getOrderId()).
                item(dispatchPreparing.getItem()).build();
        kafkaTemplate.send(DISPATCH_TRACKING_STATUS, statusUpdated).get();

    }
}
