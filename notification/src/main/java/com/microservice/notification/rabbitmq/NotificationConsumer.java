package com.microservice.notification.rabbitmq;

import com.microservice.clients.notification.CreateNotificationRequest;
import com.microservice.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(CreateNotificationRequest request) {
        log.info("Creating Notification {}", request);
        notificationService.createNotification(request);
    }
}
