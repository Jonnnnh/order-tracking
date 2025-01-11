package com.example.notificationservice.service;

import com.example.notificationservice.dto.OrderCreatedEvent;
import com.example.notificationservice.entity.Notification;
import com.example.notificationservice.mapper.NotificationMapper;
import com.example.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;


    public void processNotification(OrderCreatedEvent event) {
        log.info("Processing notification for Order ID: {}", event.getId());
        Notification notification = notificationMapper.toNotification(event);
        notificationRepository.save(notification);
        log.info("Notification processed and saved: {}", notification);
    }

    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
        log.info("Notification with ID: {} deleted as part of compensation.", notificationId);
    }
}
