package com.traceqa.traceqabackend.controller;

import com.traceqa.traceqabackend.dto.EnvironmentType;
import com.traceqa.traceqabackend.dto.ExecutionStatus;
import com.traceqa.traceqabackend.dto.TriggerSource;
import com.traceqa.traceqabackend.models.CreateExecutionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExecutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    positive flow test
    @Test
    void shouldCreateExecution_whenRequestIsValid() throws Exception {

        CreateExecutionRequest request = new CreateExecutionRequest();
        request.setExecutionName("Regression Run - Build 124");
        request.setTriggeredBy(TriggerSource.JENKINS);
        request.setEnvironment(EnvironmentType.QA);
        request.setStartTime(LocalDateTime.now());
        request.setStatus(ExecutionStatus.PASSED);

        mockMvc.perform(
                        post("/api/executions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }

//    missing field test
    @Test
    void shouldReturn400_whenExecutionNameIsMissing() throws Exception {

        CreateExecutionRequest request = new CreateExecutionRequest();
        request.setTriggeredBy(TriggerSource.CI);
        request.setEnvironment(EnvironmentType.QA);
        request.setStartTime(LocalDateTime.now());
        request.setStatus(ExecutionStatus.PASSED);

        mockMvc.perform(
                        post("/api/executions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }

    // invalid enum value
    @Test
    void shouldReturn400_whenInvalidEnumProvided() throws Exception {

        String invalidPayload = """
        {
          "executionName": "Smoke Run",
          "triggeredBy": "INVALID_SOURCE",
          "environment": "QA",
          "startTime": "2026-01-18T10:30:00",
          "status": "PASSED"
        }
        """;

        mockMvc.perform(
                        post("/api/executions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invalidPayload)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnValidationErrorMessage_whenStatusIsMissing() throws Exception {

        String payload = """
    {
      "executionName": "Regression Run",
      "triggeredBy": "CI",
      "environment": "QA",
      "startTime": "2026-01-18T10:30:00"
    }
    """;

        mockMvc.perform(
                        post("/api/executions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload)
                )
                .andExpect(status().isBadRequest());
    }

}
