package com.tomas.calculator_api.dtos;

import java.math.BigDecimal;

public record OperationResult(
        String operationId,
        BigDecimal result,
        String error
) {}
