package com.project.taskflow;

import org.springframework.boot.SpringApplication;
import org.testcontainers.utility.TestcontainersConfiguration;

public class TestTaskflowApplication {

	public static void main(String[] args) {
		SpringApplication.from(TaskflowApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
