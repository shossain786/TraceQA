package com.traceqa.traceqabackend.controller;

import com.traceqa.traceqabackend.models.CreateExecutionRequest;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/executions")
public class ExecutionController {

    private static final Log log = LogFactory.getLog(ExecutionController.class);

    @PostMapping
    public ResponseEntity<String> createExecution(@Valid @RequestBody CreateExecutionRequest request) {

        log.info("Received execution: {}" + request.getExecutionName());
        return ResponseEntity.status(HttpStatus.OK).body("Execution Succeeded");
    }
}
