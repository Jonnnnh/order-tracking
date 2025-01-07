package com.example.orderservice.orderservice.kafka;
import com.example.orderservice.orderservice.config.KafkaTopicConfig;
import com.example.orderservice.orderservice.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final KafkaTopicConfig kafkaTopicConfig;

    public void sendOrderCreatedEvent(OrderDto orderDto) {
        try {
            String ordersTopic = kafkaTopicConfig.getOrders();
            System.out.println("Orders topic: " + ordersTopic);

            String message = objectMapper.writeValueAsString(orderDto);
            kafkaTemplate.send(ordersTopic, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error of order serialization in JSON", e);
        }
    }
}
