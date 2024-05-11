package br.com.microservices.orchestrated.orderservice.core.service;

import br.com.microservices.orchestrated.orderservice.core.document.Event;
import br.com.microservices.orchestrated.orderservice.core.document.Order;
import br.com.microservices.orchestrated.orderservice.core.dto.OrderRequest;
import br.com.microservices.orchestrated.orderservice.core.kafka.producer.SagaProducer;
import br.com.microservices.orchestrated.orderservice.core.repository.OrderRepository;
import br.com.microservices.orchestrated.orderservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.String.*;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private static final String TRANSACTIONAL_ID_PATTERN = "%s_%s";
    private OrderRepository orderRepository;
    private JsonUtil jsonUtil;
    private SagaProducer sagaProducer;
    private EventService eventService;

    public Order createOrder(OrderRequest orderRequest) {
        var order = Order.builder()
                .products(orderRequest.getProducts())
                .createdAt(LocalDateTime.now())
                .transactionId(this.generateTransactionId())
                .build();
        this.orderRepository.save(order);
        sagaProducer.sendEvent(jsonUtil.toJson(this.createPayload(order)));
        return order;
    }

    private Event createPayload(Order order) {
        var event = Event.builder()
                .payload(order)
                .createdAt(LocalDateTime.now())
                .orderId(order.getId())
                .transactionId(this.generateTransactionId())
                .build();

        return eventService.save(event);
    }

    private String generateTransactionId() {
            return format(TRANSACTIONAL_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID());
    }
}
