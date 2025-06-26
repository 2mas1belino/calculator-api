package com.tomas.calculator_api.calculator_api;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorServiceTests {

    private final CalculatorService calculator = new CalculatorServiceImpl();

    @Test
    void add_TwoPositiveNumbers_ReturnsCorrectSum() {
        BigDecimal result = calculator.add(new BigDecimal("1.5"), new BigDecimal("2.3"));
        assertEquals(new BigDecimal("3.8"), result);
    }

    @Test
    void add_NegativeNumbers_ReturnsCorrectSum() {
        BigDecimal result = calculator.add(new BigDecimal("-1"), new BigDecimal("-1"));
        assertEquals(new BigDecimal("-2"), result);
    }
}
