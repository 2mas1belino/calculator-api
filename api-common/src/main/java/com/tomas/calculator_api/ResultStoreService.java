package com.tomas.calculator_api;

import com.tomas.calculator_api.dtos.OperationResult;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ResultStoreService {
    private final Map<String, OperationResult> results = new ConcurrentHashMap<>();

    public void storeResult(OperationResult result) {
        results.put(result.operationId(), result);
    }

    public Optional<OperationResult> getResult(String operationId) {
        return Optional.ofNullable(results.get(operationId));
    }
}
