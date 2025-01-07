package com.example.orderservice.orderservice.kafka;
import com.example.orderservice.orderservice.config.KafkaTopicConfig;
import com.example.orderservice.orderservice.dto.OrderDto;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaTopicConfig kafkaTopicConfig;

    public void sendOrderCreatedEvent(OrderDto orderDto) {
        String ordersTopic = kafkaTopicConfig.getOrders();
        System.out.println("Orders topic: " + ordersTopic);
        kafkaTemplate.send(ordersTopic, orderDto);
    }
}
