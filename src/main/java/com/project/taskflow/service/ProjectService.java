package com.project.taskflow.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.taskflow.dto.ProjectRequest;
import com.project.taskflow.dto.ProjectResponse;
import com.project.taskflow.entity.Project;
import com.project.taskflow.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectResponse createProject(ProjectRequest request) {
        Project project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        return mapToResponse(projectRepository.save(project));
    }

    public ProjectResponse getProjectById(UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + projectId + " not found."));
        return mapToResponse(project);
    }

    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProjectResponse updateProject(UUID projectId, ProjectRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + projectId + " not found."));
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        return mapToResponse(projectRepository.save(project));
    }

    public void deleteProjectById(UUID projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Project with ID " + projectId + " does not exist.");
        }
        projectRepository.deleteById(projectId);
    }

    private ProjectResponse mapToResponse(Project save) {
        return ProjectResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .description(save.getDescription())
                .createdAt(save.getCreatedAt())
                .build();
    }
}
