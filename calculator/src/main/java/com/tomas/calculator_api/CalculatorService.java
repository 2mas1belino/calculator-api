package com.tomas.calculator_api;

import java.math.BigDecimal;

public interface CalculatorService {
    BigDecimal add(BigDecimal a, BigDecimal b);
    BigDecimal subtract(BigDecimal a, BigDecimal b);
    BigDecimal multiply(BigDecimal a, BigDecimal b);
    BigDecimal divide(BigDecimal a, BigDecimal b) throws ArithmeticException;
}
