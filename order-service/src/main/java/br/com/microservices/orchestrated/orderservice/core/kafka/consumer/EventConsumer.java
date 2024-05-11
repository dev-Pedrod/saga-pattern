package br.com.microservices.orchestrated.orderservice.core.kafka.consumer;

import br.com.microservices.orchestrated.orderservice.core.document.Event;
import br.com.microservices.orchestrated.orderservice.core.service.EventService;
import br.com.microservices.orchestrated.orderservice.core.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventConsumer {

    private final JsonUtil jsonUtil;
    private final EventService eventService;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.notify-ending}"
    )
    public void consumeNotifyEndingEvent(String payload) {
        log.info("Receiving ending notification event {} from notify-ending topic", payload);
        Event event = jsonUtil.fromJson(payload, Event.class);
        eventService.notifyEnding(event);
    }
}
