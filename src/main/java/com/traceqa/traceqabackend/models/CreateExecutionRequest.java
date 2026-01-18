package com.traceqa.traceqabackend.models;

import com.traceqa.traceqabackend.dto.EnvironmentType;
import com.traceqa.traceqabackend.dto.ExecutionStatus;
import com.traceqa.traceqabackend.dto.TriggerSource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateExecutionRequest {
    @NotBlank(message = "Execution name is required")
    private String executionName;

    @NotNull(message = "Trigger source is required")
    private TriggerSource triggeredBy;

    @NotNull(message = "Environment is required")
    private EnvironmentType environment;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    public String getExecutionName() {
        return executionName;
    }

    public void setExecutionName(String executionName) {
        this.executionName = executionName;
    }

    public TriggerSource getTriggeredBy() {
        return triggeredBy;
    }

    public void setTriggeredBy(TriggerSource triggeredBy) {
        this.triggeredBy = triggeredBy;
    }

    public EnvironmentType getEnvironment() {
        return environment;
    }

    public void setEnvironment(EnvironmentType environment) {
        this.environment = environment;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public ExecutionStatus getStatus() {
        return status;
    }

    public void setStatus(ExecutionStatus status) {
        this.status = status;
    }

    @NotNull(message = "Status is required")
    private ExecutionStatus status;
}
