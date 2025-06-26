package com.tomas.calculator_api.controller;

import com.tomas.calculator_api.CalculatorService;
import com.tomas.calculator_api.dtos.CalculationResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/calculate")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public ResponseEntity<CalculationResponseDTO> add(
            @RequestParam("a") BigDecimal a,
            @RequestParam("b") BigDecimal b) {
        BigDecimal result = calculatorService.add(a, b);
        return ResponseEntity.ok(new CalculationResponseDTO(result));
    }

    @GetMapping("/subtract")
    public ResponseEntity<CalculationResponseDTO> subtract(
            @RequestParam("a") BigDecimal a,
            @RequestParam("b") BigDecimal b) {
        BigDecimal result = calculatorService.subtract(a, b);
        return ResponseEntity.ok(new CalculationResponseDTO(result));
    }

    @GetMapping("/multiply")
    public ResponseEntity<CalculationResponseDTO> multiply(
            @RequestParam("a") BigDecimal a,
            @RequestParam("b") BigDecimal b) {
        BigDecimal result = calculatorService.multiply(a, b);
        return ResponseEntity.ok(new CalculationResponseDTO(result));
    }

    @GetMapping("/divide")
    public ResponseEntity<CalculationResponseDTO> divide(
            @RequestParam("a") BigDecimal a,
            @RequestParam("b") BigDecimal b) {
        try {
            BigDecimal result = calculatorService.divide(a, b);
            return ResponseEntity.ok(new CalculationResponseDTO(result));
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest()
                    .body(new CalculationResponseDTO(null, e.getMessage()));
        }
    }
}
