package com.project.taskflow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.taskflow.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, UUID> {}
