package com.kalayciburak.filterservice.broker.kafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryConsumer {
    private static final Logger log = LoggerFactory.getLogger(InventoryConsumer.class);
}
