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

    /* Subtract method tests */

    @Test
    void subtract_PositiveResult_ReturnsCorrectSum() {
        BigDecimal result = calculator.subtract(new BigDecimal("5.6"), new BigDecimal("3.3"));
        assertEquals(new BigDecimal("2.3"), result);
    }

    @Test
    void subtract_NegativeResult_ReturnsCorrectSum() {
        BigDecimal result = calculator.subtract(new BigDecimal("-4"), new BigDecimal("2"));
        assertEquals(new BigDecimal("-6"), result);
    }

    @Test
    void subtract_LargeNumberDifference_ReturnsCorrectSum() {
        BigDecimal result = calculator.subtract(new BigDecimal("1000000000"), new BigDecimal("1"));
        assertEquals(new BigDecimal("999999999"), result);
    }
}
