package com.example.notificationservice.mapper;
import com.example.notificationservice.dto.OrderCreatedEvent;
import com.example.notificationservice.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "status", constant = "CREATED")
    Notification toNotification(OrderCreatedEvent event);
    OrderCreatedEvent toOrderCreatedEvent(Notification notification);
}
