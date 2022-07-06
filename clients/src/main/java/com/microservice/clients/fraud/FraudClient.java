package com.microservice.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fraud", url = "${clients.fraud.url}")
public interface FraudClient {

    @GetMapping(path = "/api/v1/fraud/{customerId}")
    FraudCheckResponse isFraudster(@PathVariable(value = "customerId") Long customerId);
}
