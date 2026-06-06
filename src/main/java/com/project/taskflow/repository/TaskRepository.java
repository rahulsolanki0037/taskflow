package com.project.taskflow.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.taskflow.entity.Task;
import com.project.taskflow.enums.Priority;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Page<Task> findByPriority(Priority priority, Pageable pageable);
}
