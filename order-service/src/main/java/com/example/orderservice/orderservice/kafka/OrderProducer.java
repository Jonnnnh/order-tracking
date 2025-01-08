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
        System.out.println("Orders topic: " + ordersTopic);
        messageProducer.sendMessage(ordersTopic, orderDto);
    }
}
