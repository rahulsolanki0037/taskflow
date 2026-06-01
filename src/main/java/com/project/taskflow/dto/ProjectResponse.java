package com.project.taskflow.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectResponse {

    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
