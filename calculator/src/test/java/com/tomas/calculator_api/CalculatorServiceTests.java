package com.tomas.calculator_api;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CalculatorServiceTests {

    private final CalculatorService calculator = new CalculatorServiceImpl();

    @Test
    void add_TwoPositiveNumbers() {
        BigDecimal result = calculator.add(new BigDecimal("1.5"), new BigDecimal("2.3"));
        assertEquals(new BigDecimal("3.8"), result);
    }

    @Test
    void add_NegativeNumbers() {
        BigDecimal result = calculator.add(new BigDecimal("-1"), new BigDecimal("-1"));
        assertEquals(new BigDecimal("-2"), result);
    }

    /* Subtract method tests */

    @Test
    void subtract_PositiveResult() {
        BigDecimal result = calculator.subtract(new BigDecimal("5.6"), new BigDecimal("3.3"));
        assertEquals(new BigDecimal("2.3"), result);
    }

    @Test
    void subtract_NegativeResult() {
        BigDecimal result = calculator.subtract(new BigDecimal("-4"), new BigDecimal("2"));
        assertEquals(new BigDecimal("-6"), result);
    }

    @Test
    void subtract_LargeNumberDifference() {
        BigDecimal result = calculator.subtract(new BigDecimal("1000000000"), new BigDecimal("1"));
        assertEquals(new BigDecimal("999999999"), result);
    }

    /* Multiply method tests */

    @Test
    void multiply_TwoPositiveNumbers() {
        BigDecimal result = calculator.multiply(new BigDecimal("3.2"), new BigDecimal("4.3"));
        assertEquals(new BigDecimal("13.76"), result);
    }

    @Test
    void multiply_NegativeNumbers() {
        BigDecimal result = calculator.multiply(new BigDecimal("-3"), new BigDecimal("-4"));
        assertEquals(new BigDecimal("12"), result);
    }

    @Test
    void multiply_DecimalPrecision() {
        BigDecimal result = calculator.multiply(new BigDecimal("0.1"), new BigDecimal("0.2"));
        assertEquals(new BigDecimal("0.02"), result);
    }

    /* Divide method tests */

    @Test
    void divide_ByOne_ReturnsOriginalNumber() {
        BigDecimal result = calculator.divide(new BigDecimal("42"), BigDecimal.ONE);
        assertEquals(new BigDecimal("42"), result); // 42 / 1 = 42
    }

    @Test
    void divide_ZeroDividend_ReturnsZero() {
        BigDecimal result = calculator.divide(BigDecimal.ZERO, new BigDecimal("5"));
        assertEquals(BigDecimal.ZERO, result); // 0 / 5 = 0
    }

    @Test
    void divide_ByZero_ThrowsArithmeticException() {
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(new BigDecimal("5"), BigDecimal.ZERO)
        );
        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void divide_RoundingBehavior_CorrectlyRoundsUp() {
        BigDecimal result = calculator.divide(new BigDecimal("1"), new BigDecimal("6"));
        // 1/6 = 0.166666... → rounds to 0.1666666667 with 10-digit HALF_UP
        assertEquals(new BigDecimal("0.1666666667"), result);
    }

    @Test
    void divide_RoundingBehavior_CorrectlyRoundsDown() {
        BigDecimal result = calculator.divide(new BigDecimal("2"), new BigDecimal("3"));
        // 2/3 = 0.666666... → rounds to 0.6666666667 with 10-digit HALF_UP
        assertEquals(new BigDecimal("0.6666666667"), result);
    }
}