package com.microservice.notification.service;

import com.microservice.clients.notification.CreateNotificationRequest;
import com.microservice.notification.persistence.model.Notification;
import com.microservice.notification.persistence.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification createNotification(CreateNotificationRequest request) {
        Notification notification = Notification.builder()
                .email(request.getEmail())
                .message(request.getMessage()).build();

        return notificationRepository.save(notification);
    }

}
