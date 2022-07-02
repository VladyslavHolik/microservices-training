package com.microservice.fraud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FraudCheckResponse {
    private Boolean isFraudster;
}
