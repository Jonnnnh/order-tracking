package com.example.notificationservice.saga;

import com.example.notificationservice.dto.OrderCreatedEvent;
import com.example.notificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
public class NotificationSagaServiceImpl implements NotificationSagaService {

    private final NotificationService notificationService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        try {
            log.info("Handling order created event: {}", event);
            notificationService.processNotification(event);

        } catch (Exception e) {
            log.error("Error processing order created event. Compensating...", e);
            compensateNotification(event);
        }
    }

    @Override
    @Transactional
    public void compensateNotification(OrderCreatedEvent event) {
        try {
            log.info("Compensating notification for event: {}", event);
            notificationService.deleteNotification(event.getId());
        } catch (Exception ex) {
            log.error("Error during compensation for order created event: {}", event, ex);
        }
    }
}
