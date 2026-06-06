package com.project.taskflow.controller;

import com.project.taskflow.dto.AssignTaskRequest;
import com.project.taskflow.dto.TaskRequest;
import com.project.taskflow.dto.TaskResponse;
import com.project.taskflow.dto.UpdateTaskStatusRequest;
import com.project.taskflow.service.TaskService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/createTask")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @GetMapping("/tasks")
    public ResponseEntity<Page<TaskResponse>> getAllTasks(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dueDate") String sortBy) {
        return ResponseEntity.ok(taskService.getTasks(page, size, sortBy));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@RequestParam UUID taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> putMethodName(@PathVariable UUID taskId, @RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.updateTask(taskId, request));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}/assign")
    public ResponseEntity<TaskResponse> assignTask(@PathVariable UUID taskId,
            @RequestBody AssignTaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.assignTask(taskId, taskRequest));
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<TaskResponse> updateStatus(@PathVariable UUID taskId,
            @RequestBody UpdateTaskStatusRequest statusRequest) {
        return ResponseEntity.ok(taskService.updateStatus(taskId, statusRequest));
    }
}
