package com.example.notificationservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.kafka.consumer")
public class KafkaConsumerProperties {
    private String groupId;
    private String autoOffsetReset;
}
