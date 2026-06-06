package com.project.taskflow.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.project.taskflow.enums.Priority;
import com.project.taskflow.enums.TaskStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskResponse {

    private UUID id;

    private String title;

    private String description;

    private TaskStatus status;

    private Priority priority;

    private LocalDate dueDate;

    private UUID projectId;

    private UUID assignedUserId;
}
