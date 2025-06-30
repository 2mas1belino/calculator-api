package com.tomas.calculator_api;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    @Override
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {return a.subtract(b);}

    @Override
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {return a.multiply(b);}

    @Override
    public BigDecimal divide(BigDecimal a, BigDecimal b) throws ArithmeticException {

        if (b.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Division by zero");
        }

        // Rounds to 10 decimal places to prevent from dividing numbers that don't terminate
        return a.divide(b, 10, RoundingMode.HALF_UP).stripTrailingZeros();
    }
}

