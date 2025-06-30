package com.tomas.calculator_api;

import com.tomas.calculator_api.dtos.OperationRequest;
import com.tomas.calculator_api.dtos.OperationResult;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorKafkaService {

    private final KafkaTemplate<String, OperationResult> kafkaTemplate;
    private final CalculatorService calculatorService;

    public CalculatorKafkaService(
            KafkaTemplate<String, OperationResult> kafkaTemplate,
            CalculatorService calculatorService) {
        this.kafkaTemplate = kafkaTemplate;
        this.calculatorService = calculatorService;
    }

    @KafkaListener(topics = "operation-requests")
    public void handleOperation(OperationRequest request) {
        try {
            BigDecimal result = switch (request.operationType()) {
                case "add" -> calculatorService.add(request.a(), request.b());
                case "subtract" -> calculatorService.subtract(request.a(), request.b());
                case "multiply" -> calculatorService.multiply(request.a(), request.b());
                case "divide" -> calculatorService.divide(request.a(), request.b());
                default -> throw new IllegalArgumentException("Unknown operation");
            };
            kafkaTemplate.send("operation-results",
                    new OperationResult(request.operationId(), result, null));
        } catch (Exception e) {
            kafkaTemplate.send("operation-results",
                    new OperationResult(request.operationId(), null, e.getMessage()));
        }
    }
}
