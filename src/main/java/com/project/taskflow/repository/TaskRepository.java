package com.project.taskflow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.taskflow.entity.Task;
import java.util.List;
import com.project.taskflow.enums.TaskStatus;


public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByStatus(TaskStatus taskStatus);
}
