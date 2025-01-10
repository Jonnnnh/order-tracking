package com.example.notificationservice.config;

import com.example.notificationservice.saga.NotificationSagaService;
import com.example.notificationservice.saga.NotificationSagaServiceImpl;
import com.example.notificationservice.service.NotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaConfig {

    @Bean
    public NotificationSagaService notificationSagaService(NotificationService notificationService) {
        return new NotificationSagaServiceImpl(notificationService);
    }
}
