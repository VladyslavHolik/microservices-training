package com.microservice.notification.controller;

import com.microservice.clients.notification.CreateNotificationRequest;
import com.microservice.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void createNotification(@RequestBody CreateNotificationRequest createNotificationRequest) {
        log.info("Creating notification: " + createNotificationRequest);
        notificationService.createNotification(createNotificationRequest);
    }
}
