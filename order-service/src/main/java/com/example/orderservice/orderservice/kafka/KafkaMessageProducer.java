package com.example.orderservice.orderservice.kafka;

import com.example.orderservice.orderservice.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaMessageProducer implements MessageProducer<OrderDto> {

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    @Override
    public void sendMessage(String topic, OrderDto message) {
        kafkaTemplate.send(topic, message)
                .whenComplete((result, exception) -> {
                    if (exception == null) {
                        log.info("Message [{}] sent to topic [{}] with offset [{}]",
                                message, topic, result.getRecordMetadata().offset());
                    } else {
                        log.error("Failed to send message [{}] to topic [{}]: {}",
                                message, topic, exception.getMessage(), exception);
                    }
                });
    }
}