package com.project.taskflow.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.taskflow.dto.AssignTaskRequest;
import com.project.taskflow.dto.TaskRequest;
import com.project.taskflow.dto.TaskResponse;
import com.project.taskflow.dto.UpdateTaskStatusRequest;
import com.project.taskflow.entity.Project;
import com.project.taskflow.entity.Task;
import com.project.taskflow.entity.User;
import com.project.taskflow.enums.TaskStatus;
import com.project.taskflow.repository.ProjectRepository;
import com.project.taskflow.repository.TaskRepository;
import com.project.taskflow.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskResponse createTask(TaskRequest request) {
        Project project = projectRepository.findById(request.getProjectId()).orElseThrow();

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(TaskStatus.TODO)
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .assignedProject(project)
                .build();

        return mapToResponse(taskRepository.save(task));
    }

    public Page<TaskResponse> getTasks(int page, int size, String sortBy) {
        return taskRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy))).map(this::mapToResponse);
    }

    public TaskResponse getTaskById(UUID taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        return mapToResponse(task);
    }

    public TaskResponse updateTask(UUID taskId, TaskRequest request) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        Project project = projectRepository.findById(request.getProjectId()).orElseThrow();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setAssignedProject(project);

        return mapToResponse(taskRepository.save(task));
    }

    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }

    public TaskResponse assignTask(UUID taskId, AssignTaskRequest taskRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        User user = userRepository.findById(taskRequest.getUserId()).orElseThrow();
        task.setAssignedUser(user);
        return mapToResponse(taskRepository.save(task));
    }

    public TaskResponse updateStatus(UUID taskId, UpdateTaskStatusRequest statusRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(statusRequest.getStatus());
        return mapToResponse(taskRepository.save(task));
    }

    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .projectId(task.getAssignedProject().getId())
                .assignedUserId(task.getAssignedUser() != null ? task.getAssignedUser().getId()  : null)
                .build();
    }
}
