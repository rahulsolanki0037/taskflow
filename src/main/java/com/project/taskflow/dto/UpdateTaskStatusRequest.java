package com.project.taskflow.dto;

import com.project.taskflow.enums.TaskStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskStatusRequest {

    private TaskStatus status;
}
