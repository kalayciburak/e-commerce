package com.kalayciburak.commonpackage.messaging.broker;

import com.kalayciburak.commonpackage.messaging.event.BaseEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseProducer {
    private static final Logger log = LoggerFactory.getLogger(BaseProducer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public <T extends BaseEvent> void sendMessage(T event, String topic) {
        log.info("{} event => {}", topic, event.toString());
        Message<T> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        kafkaTemplate.send(message);
    }
}