package com.microservice.fraud.controller;

import com.microservice.clients.fraud.FraudCheckResponse;
import com.microservice.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/fraud")
public class FraudController {
    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Long customerId) {
        log.info("Fraud check for customer with id  {}", customerId);
        return new FraudCheckResponse(fraudCheckService.isFraudulentCustomer(customerId));
    }
}
