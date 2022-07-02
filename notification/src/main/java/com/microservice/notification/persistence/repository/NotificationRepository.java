package com.microservice.notification.persistence.repository;

import com.microservice.notification.persistence.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
