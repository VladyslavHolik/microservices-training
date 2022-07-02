package com.microservice.customer.service;

import com.microservice.customer.dto.FraudCheckResponse;
import com.microservice.customer.dto.RegisterRequest;
import com.microservice.customer.persistence.model.Customer;
import com.microservice.customer.persistence.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(RegisterRequest registerRequest) {
        Customer customer = Customer.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail()).build();

        customer = customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
                );

        assert fraudCheckResponse != null;
        if (fraudCheckResponse.getIsFraudster()) {
            throw new IllegalStateException("Customer is fraudster");
        }
    }
}
