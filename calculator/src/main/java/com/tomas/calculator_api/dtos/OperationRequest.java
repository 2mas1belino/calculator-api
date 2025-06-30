package com.tomas.calculator_api.dtos;


import java.math.BigDecimal;

public record OperationRequest(
        String operationId,
        String operationType,  // "add", "subtract", etc.
        BigDecimal a,
        BigDecimal b
) {}
