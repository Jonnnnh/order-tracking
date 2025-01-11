package com.example.notificationservice.listener;

import com.example.notificationservice.dto.OrderCreatedEvent;
import com.example.notificationservice.saga.NotificationSagaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreatedListener {

    private final NotificationSagaService notificationSagaService;

    @KafkaListener(topics = "orders", groupId = "notification-group")
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Order creation event received: {}", event);
        try {
            notificationSagaService.handleOrderCreatedEvent(event);
        } catch (Exception e) {
            log.error("Error processing order created event: {}", event, e);
        }
    }
}
