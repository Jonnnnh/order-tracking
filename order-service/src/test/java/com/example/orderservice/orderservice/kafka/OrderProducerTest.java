package com.example.orderservice.orderservice.kafka;

import com.example.orderservice.orderservice.config.KafkaTopicConfig;
import com.example.orderservice.orderservice.dto.OrderDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class OrderProducerTest {
    @Test
    void testSendOrderCreatedEvent() {
        KafkaTopicConfig kafkaTopicConfig = Mockito.mock(KafkaTopicConfig.class);
        Mockito.when(kafkaTopicConfig.getOrders()).thenReturn("test-orders-topic");
        MessageProducer<OrderDto> messageProducer = Mockito.mock(MessageProducer.class);
        OrderProducer orderProducer = new OrderProducer(messageProducer, kafkaTopicConfig);
        OrderDto orderDto = new OrderDto(1L, "Product A", 2, BigDecimal.valueOf(100));
        orderProducer.sendOrderCreatedEvent(orderDto);
        Mockito.verify(messageProducer).sendMessage("test-orders-topic", orderDto);
    }
}