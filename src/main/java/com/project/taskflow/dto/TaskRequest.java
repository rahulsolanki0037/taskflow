package com.project.taskflow.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.project.taskflow.enums.Priority;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    @NotBlank
    private String title;

    private String description;

    private Priority priority;

    private LocalDate dueDate;

    private UUID projectId;
}
