package com.dispatch.tracking.util;

import com.dispatch.tracking.message.DispatchPreparing;

import java.util.UUID;

public class TestEventData {

    public static DispatchPreparing buildDispatchPreparing(UUID orderId, String item) {
        return DispatchPreparing.builder().orderId(orderId).item(item).build();
    }
}
