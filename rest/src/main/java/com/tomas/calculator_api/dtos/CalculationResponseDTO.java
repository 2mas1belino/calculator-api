package com.tomas.calculator_api.dtos;

import java.math.BigDecimal;

public class CalculationResponseDTO {
    private final BigDecimal result;
    private final String error;

    public CalculationResponseDTO(BigDecimal result) {
        this.result = result;
        this.error = null;
    }

    public CalculationResponseDTO(BigDecimal result, String error) {
        this.result = result;
        this.error = error;
    }

    // Getters
    public BigDecimal getResult() {
        return result;
    }

    public String getError() {
        return error;
    }
}

