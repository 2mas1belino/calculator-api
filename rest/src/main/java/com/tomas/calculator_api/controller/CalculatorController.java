package com.tomas.calculator_api.controller;

import com.tomas.calculator_api.CalculatorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculate")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public ResponseEntity<CalculationResponse> add(
            @RequestParam("a") BigDecimal a,
            @RequestParam("b") BigDecimal b) {
        BigDecimal result = calculatorService.add(a, b);
        return ResponseEntity.ok(new CalculationResponse(result));
    }

}
