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

    /* Multiply method tests */

    @Test
    void multiply_TwoPositiveNumbers_ReturnsCorrectResult() {
        BigDecimal result = calculator.multiply(new BigDecimal("3.2"), new BigDecimal("4.3"));
        assertEquals(new BigDecimal("13.76"), result);
    }

    @Test
    void multiply_NegativeNumbers_ReturnsCorrectResult() {
        BigDecimal result = calculator.multiply(new BigDecimal("-3"), new BigDecimal("-4"));
        assertEquals(new BigDecimal("12"), result);
    }

    @Test
    void multiply_DecimalPrecision_ReturnsCorrectResult() {
        BigDecimal result = calculator.multiply(new BigDecimal("0.1"), new BigDecimal("0.2"));
        assertEquals(new BigDecimal("0.02"), result);
    }
}
