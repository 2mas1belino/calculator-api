package com.tomas.calculator_api.controller;

import com.tomas.calculator_api.dtos.OperationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/calculate")
public class CalculatorController {

    private final KafkaTemplate<String, OperationRequest> kafkaTemplate;

    public CalculatorController(KafkaTemplate<String, OperationRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/add")
    public ResponseEntity<String> add(
            @RequestParam("a") BigDecimal a,
            @RequestParam("b") BigDecimal b) {

        String operationId = UUID.randomUUID().toString();
        OperationRequest request = new OperationRequest(operationId, "add", a, b);

        kafkaTemplate.send("operation-requests", request);

        return ResponseEntity.accepted()
                .header("X-Operation-ID", operationId)
                .body("Calculation enqueued. Use operation ID to fetch results.");
    }

    /*@GetMapping("/subtract")
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
    }*/
}
