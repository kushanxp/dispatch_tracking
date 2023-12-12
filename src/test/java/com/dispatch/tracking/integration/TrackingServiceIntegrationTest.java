package com.dispatch.tracking.integration;

import com.dispatch.tracking.TrackingServiceConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@EmbeddedKafka(controlledShutdown = true)
@DirtiesContextK
@ActiveProfiles("test")
@SpringBootTest(classes = {TrackingServiceConfiguration.class})
public class TrackingServiceIntegrationTest {
}
