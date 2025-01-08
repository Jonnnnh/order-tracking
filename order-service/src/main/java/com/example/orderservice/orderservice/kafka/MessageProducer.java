package com.example.orderservice.orderservice.kafka;

public interface MessageProducer<T> {
    void sendMessage(String topic, T message);
}
