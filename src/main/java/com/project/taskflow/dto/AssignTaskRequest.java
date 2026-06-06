package com.project.taskflow.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignTaskRequest {

    private UUID userId;
}
