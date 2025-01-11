package com.example.notificationservice.mapper;

import com.example.notificationservice.dto.OrderCreatedEvent;
import com.example.notificationservice.entity.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class NotificationMapperTest {

    @Autowired
    private NotificationMapper notificationMapper;

    @Test
    public void testToNotification() {
        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .id(1L)
                .product("Laptop")
                .quantity(2)
                .price(BigDecimal.valueOf(1200.50))
                .build();

        Notification notification = notificationMapper.toNotification(event);

        assertNull(notification.getId());
        assertEquals(event.getProduct(), notification.getProduct());
        assertEquals(event.getQuantity(), notification.getQuantity());
        assertEquals(event.getPrice(), notification.getPrice());
        assertEquals("CREATED", notification.getStatus());
    }

    @Test
    public void testToOrderCreatedEvent() {
        Notification notification = Notification.builder()
                .id(1L)
                .product("Laptop")
                .quantity(2)
                .price(BigDecimal.valueOf(1200.50))
                .status("CREATED")
                .build();

        OrderCreatedEvent event = notificationMapper.toOrderCreatedEvent(notification);

        assertEquals(notification.getId(), event.getId());
        assertEquals(notification.getProduct(), event.getProduct());
        assertEquals(notification.getQuantity(), event.getQuantity());
        assertEquals(notification.getPrice(), event.getPrice());
    }
}
