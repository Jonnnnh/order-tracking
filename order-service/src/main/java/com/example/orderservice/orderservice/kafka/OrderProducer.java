package com.example.orderservice.orderservice.kafka;
import com.example.orderservice.orderservice.config.KafkaTopicConfig;
import com.example.orderservice.orderservice.dto.OrderDto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@AllArgsConstructor
public class OrderProducer {

    private final MessageProducer<OrderDto> messageProducer;
    private final KafkaTopicConfig kafkaTopicConfig;

    public void sendOrderCreatedEvent(OrderDto orderDto) {
        String ordersTopic = kafkaTopicConfig.getOrders();
        log.info("Preparing to send message to topic: {}", ordersTopic);

        try {
            messageProducer.sendMessage(ordersTopic, orderDto);
            log.info("Message sent successfully to topic: {}", ordersTopic);
        } catch (Exception e) {
            log.error("Error sending message to topic {}: {}", ordersTopic, e.getMessage(), e);
        }
    }
}
