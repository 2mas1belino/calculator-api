package com.tomas.calculator_api.controller;

import com.tomas.calculator_api.ResultStoreService;
import com.tomas.calculator_api.dtos.OperationRequest;
import com.tomas.calculator_api.dtos.OperationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/calculate")
public class CalculatorController {

    private final KafkaTemplate<String, OperationRequest> kafkaTemplate;
    private final ResultStoreService resultStoreService;

    public CalculatorController(
            KafkaTemplate<String, OperationRequest> kafkaTemplate,
            ResultStoreService resultStoreService) {
        this.kafkaTemplate = kafkaTemplate;
        this.resultStoreService = resultStoreService;
    }

    @GetMapping("/sum")
    public ResponseEntity<String> add(
            @RequestParam("a") BigDecimal a,
            @RequestParam("b") BigDecimal b) {
        return enqueueOperation(a, b, "add");
    }

    @GetMapping("/subtract")
    public ResponseEntity<String> subtract(
            @RequestParam("a") BigDecimal a,
            @RequestParam("b") BigDecimal b) {
        return enqueueOperation(a, b, "subtract");
    }

    @GetMapping("/multiply")
    public ResponseEntity<String> multiply(
            @RequestParam("a") BigDecimal a,
            @RequestParam("b") BigDecimal b) {
        return enqueueOperation(a, b, "multiply");
    }

    @GetMapping("/divide")
    public ResponseEntity<String> divide(
            @RequestParam("a") BigDecimal a,
            @RequestParam("b") BigDecimal b) {
        return enqueueOperation(a, b, "divide");
    }

    private ResponseEntity<String> enqueueOperation(BigDecimal a, BigDecimal b, String operation) {
        String operationId = UUID.randomUUID().toString();
        OperationRequest request = new OperationRequest(operationId, operation, a, b);

        kafkaTemplate.send("operation-requests", request);

        return ResponseEntity.accepted()
                .header("X-Operation-ID", operationId)
                .body("Calculation enqueued. Use operation ID to fetch results.");
    }

    @GetMapping("/results/{operationId}")
    public ResponseEntity<OperationResult> getResult(
            @PathVariable String operationId) {
        return resultStoreService.getResult(operationId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
