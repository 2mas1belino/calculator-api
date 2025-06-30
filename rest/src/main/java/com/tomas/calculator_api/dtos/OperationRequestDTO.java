package com.tomas.calculator_api.dtos;


import java.math.BigDecimal;

public record OperationRequestDTO(
        String operationId,
        String operationType,
        BigDecimal a,
        BigDecimal b
) {}
