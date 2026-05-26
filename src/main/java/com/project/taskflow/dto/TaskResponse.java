package com.project.taskflow.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {

    private UUID id;

    private String title;

    private String description;

    private String status;
}
