package com.example.notificationservice.saga;

import com.example.notificationservice.dto.OrderCreatedEvent;

public interface NotificationSagaService {

    void handleOrderCreatedEvent(OrderCreatedEvent event);

    void compensateNotification(OrderCreatedEvent event);
}
