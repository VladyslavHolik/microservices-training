package com.microservice.customer.service;

import com.microservice.clients.fraud.FraudCheckResponse;
import com.microservice.clients.fraud.FraudClient;
import com.microservice.clients.notification.CreateNotificationRequest;
import com.microservice.clients.notification.NotificationClient;
import com.microservice.customer.dto.RegisterRequest;
import com.microservice.customer.persistence.model.Customer;
import com.microservice.customer.persistence.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(RegisterRequest registerRequest) {
        Customer customer = Customer.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail()).build();

        customer = customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        notificationClient.createNotification(new CreateNotificationRequest(customer.getEmail(), "Account was successfully created"));

        assert fraudCheckResponse != null;
        if (fraudCheckResponse.getIsFraudster()) {
            throw new IllegalStateException("Customer is fraudster");
        }
    }
}
