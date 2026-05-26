package com.project.taskflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.taskflow.dto.TaskRequest;
import com.project.taskflow.dto.TaskResponse;
import com.project.taskflow.entity.Task;
import com.project.taskflow.enums.TaskStatus;
import com.project.taskflow.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskResponse createTask(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.TODO);
        task.setPriority(request.getPriority());

        Task createTask = taskRepository.save(task);
        return mapToResponse(createTask);
    }

    public List<TaskResponse> getTasks() {
        return taskRepository.findAll()
                    .stream()
                    .map(this::mapToResponse)
                    .toList();
    }

    private TaskResponse mapToResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setStatus(task.getStatus().name());

        return taskResponse;
    }
}
